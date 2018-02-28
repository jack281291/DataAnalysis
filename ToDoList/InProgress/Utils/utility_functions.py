import pandas as pd
import numpy as np

#***********************************************************
#*************** DESCRIPTIVE ANALYSIS **********************
#***********************************************************

def name_cleaning(df):
    """
    Functions that returns the dataframe with the cleaned columns using a standard conversion
    """
    df.columns = [re.sub("[\\. \\(\\)\\/]+", "_", elem) for elem in df.columns]
    df.columns = [re.sub("-", "_", elem) for elem in df.columns]
    df.columns = [re.sub("'", "", elem) for elem in df.columns]
    df.columns = [re.sub(",", "_", elem) for elem in df.columns]
    df.columns = [re.sub(":", "_", elem) for elem in df.columns]
    df.columns = [re.sub("<", "MIN", elem) for elem in df.columns]
    df.columns = [re.sub(">", "MAG", elem) for elem in df.columns]
    df.columns = [re.sub("&", "E", elem) for elem in df.columns]
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

def first_n_unique_values(df, col, n):
    """
    Function that returns the first n unique values of a given column of the dataframe df
    """
    unique_values = df[col][~df[col].isnull()].unique().tolist()
    if len(unique_values) < n:
        return unique_values
    else:
        return unique_values[:n]

def number_missing_values(df):
    """
    Functions that returns the number of missing values as a dataframe
    """
    missing_values = np.sum(df.isnull())/df.shape[0]
    return pd.DataFrame(missing_values.rename("Missing Values"))

def descriptiveS(df, n):
    """
    Function that returns a pandas dataframe with the descriptives statistics
    Inputs:
        n -> number of example values that you want to see in the output
    """
    df_describe = df.describe(include="all").T
    df_describe["Type"] = df.dtypes
    df_describe = df_describe[list(df_describe.columns)[-1:] + list(df_describe.columns)[0:-1]]
    df_describe["First " + str(n) + " values"] = df_describe.index.map(lambda x: df[x].dropna().unique()[:5])
    return df_describe

def find_duplicates(df, cols):
    """
    Function that returns the rows of the dataframe with duplicates
    """
    return df[df[cols].duplicated(keep = False)]

#***********************************************************
#******************* PRE-PROCESSING ************************
#***********************************************************

def standardize(df, cols):
    """
    Function that takes the columns as a list and give back the dataframe with the standardized columns
    """
    df_new = df.copy()
    for col in cols:
        df_new[col] = (df_new[col] - np.mean(df_new[col]))/np.std(df_new[col])
    return df_new

def normalize(df, cols):
    """
    Function that takes the columns as a list and give back the dataframe with the normalized columns
    """
    df_new = df.copy()
    for col in cols:
        df_new[col] = (df_new[col] - np.min(df_new[col]))/(np.max(df_new[col]) - np.min(df_new[col]))
        return df_new

def desc_qual_col(df, col_x, col_y, k):
    """
    Function that returns a dataframe with unique value, count of col_x and the mean of col_y
    """
    df_ = pd.DataFrame(columns=["unique", "count", "mean_value"])
    for mod in df[col_x].unique():
        df_ = df_.append({"unique":mod, "count":np.sum(df[col_x] == mod), "mean_value_poor": 
                          df[col_y][df[col_x] == mod].mean()}, ignore_index=True)
        df_ = df_[df_["count"] > k]
    return df_

def parallelized_groupby(df, keys, f):
    """
    Inputs:
        - keys -> list of columns
        - f -> dictionary in the form {variable: {"variable_name_mean":np.mean,
               "variable_name_min":np.min}} (does not work for single aggregation function)
    """
    grouped = df.groupby(keys)
    df_s = []
    for index in grouped.indices.values():
        df_s.append(df.iloc[index])
    func = delayed(groupby_function)
    res = Parallel(n_jobs=4, verbose=10)(func(s, keys, f) for s in df_s)
    return pd.concat(res)

def apply_df(df, f): 
    """
    inputs:
        - df -> dataframe
        - f -> function that takes row as an input and do some operation along row
    """
    return df.apply(f, axis=1) 

def parallelized_apply(df, apply_func, f, num_partitions):
    """
    Inputs:
        - df -> dataframe
        - apply_func -> function that takes row and f as an input and return df.apply()
        - f -> input of the apply_df
    """
    func = delayed(apply_func) 
    res = Parallel(n_jobs=4, verbose=10)(func(s, f=f) for s in np.array_split(df, num_partitions)) 
    return pd.concat(res) 


#****************************************************************
#******************* FEATURE ENGINEERING ************************
#****************************************************************


def combine_feature(df, cols_list):
    df_ = df.copy()
    for col_1 in cols_list:
        for col_2 in cols_list:
            if col_1 < col_2:
                df_[col_1 + "_on_" + col_2] = 1
                df_[col_1 + "_on_" + col_2][df_[col_2] != 0] = df_[col_1] / df_[col_2]
                df_[col_1 + "_on_" + col_2][(df_[col_1].isnull()) | df_[col_2].isnull() ] = -1
                df_[col_1 + "_times_" + col_2] = df_[col_1] * df_[col_2]
    return df_


#************************************************************************
#******************* HYPERPARAMETER OPTIMIZATION ************************
#************************************************************************


def cross_validation_scores_class(X_train, y_train, model, n_folds):
    """
    Function that returns the scores of the n_folds iteration of the model training/prediction, if you take the mean
    you have an estimation of the performance of the model
    """
    scores = X_train.copy()
    scores["PRED_PROB"] = 0
    cv = StratifiedKFold(n_splits=n_folds, shuffle=True, random_state=101).split(X_train, y_train)
    for i, (train, test) in enumerate(cv):
        model.fit(X_train.loc[train], y_train.loc[train])
        scores["PRED_PROB"].loc[test] = model.predict_proba(X_train.loc[test])[:,1]
    return scores

def cross_validation_scores_regr(X_train, y_train, model, n_folds):
    """
    Function that returns the scores of the n_folds iteration of the model training/prediction, if you take the mean
    you have an estimation of the performance of the model
    """
    scores = X_train.copy()
    scores["PREDICTION"] = 0
    cv = StratifiedKFold(n_splits=n_folds, shuffle=True, random_state=101).split(X_train, y_train)
    for i, (train, test) in enumerate(cv):
        model.fit(X_train.loc[train], y_train.loc[train])
        scores["PREDICTION"].loc[test] = model.predict(X_train.loc[test])
    return scores