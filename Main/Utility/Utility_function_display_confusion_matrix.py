import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sn

def plot_confusion_matrix(confusion_matrix, figsize = (10, 7), indexes, columns):
  """
  Function that plots the confusion matrix (it has to be a numpy array)
  """
  df_temp = pd.DataFrame(confusion_matrix, index = indexes, columns = columns)
  plt.figure(figsize= figsize)
  sn.heatmap(df_temp, annot = True)