def check_CF(cf):
    """
    Check of the Codice Fiscale
    """
    cf=str(cf)
    # lunghezza
    if (len(cf)!=16):
        return False
    # ANNO NASCITA FID
    if not cf[6:8].isdigit():
        return False
    # MESE NASCITA
    if cf[8].isdigit():
        return False
    # SESSO
    if not cf[9:11].isdigit():
        return False
    # STRANIERO
    if cf[11].isdigit():
        return False
    return True

def CF_KPIs(data, cf_col, df_catasto_geo_path):
    """
    It return a series of KPIs from the CF of the subject
    """
    # GEO TABLE
    catasto_geo = pd.read_csv(df_catasto_geo_path,sep=',',dtype=object,encoding='ISO-8859-1')
    catasto_geo= catasto_geo.rename(columns=lambda x: x.upper())
    # ANNO NASCITA FID
    data[cf_col + '_ANNO_NASCITA'] =data[cf_col].map(lambda x: int(str(x)[6:8])+1900)
    # MESE NASCITA
    data[cf_col + "_MESE_NASCITA"] =data[cf_col].map(lambda x: str(x)[8])
    # SESSO
    data[cf_col + "_SESSO"] = data[cf_col].map(lambda x: 'F' if int(str(x)[9:11])>35 else 'M')
    # NAZIONALITA
    data[cf_col + "_NAZIONALITA_STRANIERA"] =data[cf_col].map(lambda x: 1 if str(x)[11]=='Z' else 0)
    # CITTA' DI NASCITA
    data[cf_col + "_F_CITTA_NASCITA"] = data[cf_col].map(lambda x: str(x)[11:15])
    # GEOCODING
    catasto_geo[cf_col + "_F_CITTA_NASCITA"] = catasto_geo["CODICE CATASTALE DEL COMUNE"]
    catasto_geo[cf_col + "_LAT"] = catasto_geo["LATITUDINE"]
    catasto_geo[cf_col + "_LON"] = catasto_geo["LONGITUDINE"]
    catasto_geo = catasto_geo[[cf_col+"_F_CITTA_NASCITA", cf_col+ "_LAT", cf_col+ "_LON"]].drop_duplicates()
    data=pd.merge(left=data,right=catasto_geo,how='left',on=[cf_col+"_F_CITTA_NASCITA"])
    return data

def CAP_KPIs(data, col, df_cap_path):
    """
    It returns KPIs from the CAP
    """
    CAP_geo=pd.read_csv(df_cap_path, sep=",", dtype=object)
    CAP_geo= CAP_geo.rename(columns=lambda x: col+'_'+x)
    CAP_geo.columns.values[0] = col
    CAP_geo[col]=CAP_geo[col].astype(str)
    data=pd.merge(left=data,right=CAP_geo,how='left',on=[col])
    return data
