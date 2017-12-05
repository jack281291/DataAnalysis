import matplotlib.pyplot as plt
import seaborn as sns

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

def make_pairplot(df, col_subset="all", hue=None, diag_kind="hist",size=2.5):
    """
    Inputs
        - hue -> categorical feature (often the target variable)
        - diag_kind -> hist, kde
    """
    if col_subset == "all":
        sns.pairplot(df,hue=hue,diag_kind=diag_kind,size=size)
    else:
        sns.pairplot(df[col_subset],hue=hue,diag_kind=diag_kind,size=size)
        
def make_scatterplot(df, col_x, col_y,size=6):
    sns.jointplot(x=col_x, y=col_y, data=df,size=size)
    
def make_scatterplot_with_hue(df, col_x, col_y,hue=None,size=5):
    """
    Inputs:
        - hue -> categorical feature (often the target variable)
    """
    sns.FacetGrid(df, hue=hue,size=size) \
   .map(plt.scatter, col_x, col_y) \
   .add_legend()
def make_hexbin_plot(df, col_x, col_y,size=6):
    """
    The bivariate analogue of a histogram is known as a “hexbin” plot, because it shows the 
    counts of observations that fall within hexagonal bins.
    """
    with sns.axes_style("white"):
        sns.jointplot(x=df[col_x], y=df[col_y], kind="hex", color="k",size=size)

def number_missing_values(df):
    missing_values = np.sum(df.isnull())/df.shape[0]
    return pd.DataFrame(missing_values.rename("Missing Values"))

def descriptive(df, n):
    """
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
    It returns the rows of the dataframe with duplicates
    """
    return df[df[cols].duplicated(keep = False)]

def first_n_unique_values(df, col, n):
    """
    It Returns the first n unique values of a given column of the dataframe df
    """
    unique_values = df[col][~df[col].isnull()].unique().tolist()
    if len(unique_values) < n:
        return unique_values
    else:
        return unique_values[:n]