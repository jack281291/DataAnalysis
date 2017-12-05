import re

def name_cleaning(df):
    """
    Function that cleanes the names of a pandas dataframe following a custom convention.
    Args
    ----
    df -- pandas DataFrame
    
    Returns
    -------
    df -- pandas DataFrame with the cleaned columns
    """
    # Custom cleaning
    df.columns = [re.sub("[\\. \\(\\)\\/]+", "_", elem) for elem in df.columns]
    df.columns = [re.sub("-", "_", elem) for elem in df.columns]
    df.columns = [re.sub("'", "", elem) for elem in df.columns]
    df.columns = [re.sub(",", "_", elem) for elem in df.columns]
    df.columns = [re.sub(":", "_", elem) for elem in df.columns]
    df.columns = [re.sub("Â°", "", elem) for elem in df.columns]
    df.columns = [re.sub("%", "PERC", elem) for elem in df.columns]
    df.columns = [re.sub("\\+", "_", elem) for elem in df.columns]
    # String upper
    df.columns = [elem.upper() for elem in df.columns]
    # Trim
    df.columns = [elem.strip() for elem in df.columns]
    # Cut recurring underscore
    df.columns = [re.sub("_+", "_", elem) for elem in df.columns]
    return(df)