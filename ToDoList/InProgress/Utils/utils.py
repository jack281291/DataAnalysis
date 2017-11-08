# Import libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import scipy.stats as stats
import time
from sklearn import preprocessing
import sklearn.feature_selection as selection
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import StratifiedKFold
from sklearn.feature_selection import RFECV
from sklearn.cross_validation import train_test_split
from sklearn.grid_search import GridSearchCV
from sklearn.metrics import classification_report
from sklearn import metrics
from collections import defaultdict
import xgboost as xgb
import multiprocessing as mp
from joblib import Parallel, delayed

class general(df):

    def nameCleaning(df):
        """
        Function that takes as input a pandas DataFrame and returns the same dataframe with clean columns.

        Args
        ----
        df -- pandas DataFrame
        
        Returns
        -------
        df -- pandas DataFrame

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

class DataExploration():