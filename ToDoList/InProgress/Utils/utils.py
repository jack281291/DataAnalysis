# Import libraries

import re
import numpy as np
import pandas as pd

# import matplotlib.pyplot as plt
# import seaborn as sns
# import scipy.stats as stats
# import time
# from sklearn import preprocessing
# import sklearn.feature_selection as selection
# from sklearn.ensemble import RandomForestClassifier
# from sklearn.model_selection import StratifiedKFold
# from sklearn.feature_selection import RFECV
# from sklearn.cross_validation import train_test_split
# from sklearn.grid_search import GridSearchCV
# from sklearn.metrics import classification_report
# from sklearn import metrics
# from collections import defaultdict
# import xgboost as xgb
# import multiprocessing as mp
# from joblib import Parallel, delayed

# ************************************************************************
# **************************** PRE-PROCESSING ****************************
# ************************************************************************

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

# ***********************************************************************************
# **************************** EXPLORATORY DATA ANALYSIS ****************************
# ***********************************************************************************

def make_hist(df, col, nbins=None, kde=False):
    """
    Inputs:
        - kde -> kernel density estimate, a way to esimate the probability density function of a 
                 random variable
    """
    sns.set_style('dark')
    sns.utils.axlabel(col, 'Frequency')
    sns.distplot(df[col], bins=nbins,kde=kde)

def compare_parametrical_distribution(df, col, nbins=None, par_distr = stats.gamma):
    """
    Fit a parametric distribution to a dataset and visually evaluate how closely it corresponds
    to the observed data (default gamma)
    """
    sns.distplot(df[col], kde=False, bins=nbins, fit=stats.gamma)
    
def make_boxplot(df, quant_col, qual_col=None):
    sns.set_style('dark')
    sns.boxplot(df[qual_col],df[quant_col])