def find_duplicates(df, columns):
  return df[df[col].duplicated(keep = False)]
