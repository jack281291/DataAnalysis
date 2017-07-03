import pandas as pd
import numpy as np
from sklearn.cross_validation import StratifiedKFold

def cv_evaluation_range(model, n_fold, X_train, y_train, score_function):
    scores   = []
    cv = StratifiedKFold(y_train, n_folds=n_fold, random_state=1001)
    enumerate(cv)
    for i, (t, v) in enumerate(cv):
        # train then immediately predict the test set
        y_hat = model.fit(X_train.loc[t], y_train.loc[t]).predict(X_train.loc[v])
        # stash the overall error on the test set for the fold too
        scores.append( score_function(y_train[v], y_hat) )
    return [np.mean(scores) - 1.96*np.sd(scores), np.mean(scores) + 1.96*np.sd(scores)]
