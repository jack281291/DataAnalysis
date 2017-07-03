"""
Memory-based algorithms are easy to implement and produce reasonable prediction quality. The drawback of memory-based CF is that it doesn’t scale to real-world scenarios and doesn’t address the well-known cold-start problem (a new user or a new item enters the system).
At the same time, applying SVD requires factorization of the user-item matrix which can be problematic when the matrix is very sparse.
Therefore, more recent work has investigated how to model the observed ratings directly by minimizing the regularized squared error with respect to a latent user feature matrix P and a latent movie feature matrix Q on the known ratings:

https://latex.codecogs.com/gif.latex?\underset{Q*&space;,&space;P*}{min}\sum_{(u,i)\epsilon&space;K&space;}(r_{ui}-P_u^TQ_i)^2+\lambda(\left&space;\|&space;Q_i&space;\right&space;\|^2&space;+&space;\left&space;\|&space;P_u&space;\right&space;\|^2)$&space;&space;$(1)

The Regularized squared error is the loss function that you want to minimize. After you have estimated P and Q by minimizing the regularized squared error you can predict the unknown ratings by taking the dot product of the latent features for users and items.

You can apply stochastic gradient descent (SGD) or alternating least square (ALS) to minimize the loss function. Both SGD or ALS can be used for online learning, i.e. updating your model incrementaly each time a new rating comes in. Here we will apply SGD.
"""

################################################################################
############################## PRE-PROCESSING ##################################
################################################################################

"""
Again using the MovieLens dataset, you should add the unzipped dataset folder to your notebook directory.
"""
import numpy as np
import pandas as pd
header = ['user_id', 'item_id', 'rating', 'timestamp']
df = pd.read_csv('./ml-100k/u.data', sep='\t', names=header)
n_users = df.user_id.unique().shape[0]
n_items = df.item_id.unique().shape[0]
print( 'Number of users = ' + str(n_users) + ' | Number of movies = ' + str(n_items) )

from sklearn import cross_validation as cv
train_data, test_data = cv.train_test_split(df,test_size=0.25)

train_data = pd.DataFrame(train_data)
test_data = pd.DataFrame(test_data)

# Create training and test matrix
R = np.zeros((n_users, n_items))
for line in train_data.itertuples():
    R[line[1]-1, line[2]-1] = line[3]

T = np.zeros((n_users, n_items))
for line in test_data.itertuples():
    T[line[1]-1, line[2]-1] = line[3]

# Index matrix for training data
I = R.copy()
I[I > 0] = 1
I[I == 0] = 0

# Index matrix for test data
I2 = T.copy()
I2[I2 > 0] = 1
I2[I2 == 0] = 0

################################################################################
######## STOCHASTIC GRADIENT DESCENT WITH WIGHTED LAMBDA REGULARISATION ########
################################################################################
"""
When using CF for stochastic gradient descent, you want to estimate two matrices – the latent user feature matrix P and the latent movie feature matrix Q. After you have estimated P and Q you can then predict the unknown ratings by taking the dot product of the latent features for users and items.
"""
# Predict the unknown ratings through the dot product of the latent features for users and items
def prediction(P,Q):
    return np.dot(P.T,Q)
"""
Let’s start by defining the hyperparamters λ (regularisation weight) and k (dimensionality of the latent feature space) and initialising the latent factor matrices P and Q.
"""
lmbda = 0.1 # Regularisation weight
k = 20  # Dimensionality of the latent feature space
m, n = R.shape  # Number of users and items
n_epochs = 100  # Number of epochs
gamma=0.01  # Learning rate

P = 3 * np.random.rand(k,m) # Latent user feature matrix
Q = 3 * np.random.rand(k,n) # Latent movie feature matrix

"""
There are many evaluation metrics but one of the most popular metrics used to evaluate accuracy of predicted ratings is Root Mean Squared Error (RMSE), which we will use here.
"""
# Calculate the RMSE
def rmse(I,R,Q,P):
    return np.sqrt(np.sum((I * (R - prediction(P,Q)))**2)/len(R[R > 0]))

"""
Now we can implement SGD-WR:
"""
train_errors = []
test_errors = []

#Only consider non-zero matrix
users,items = R.nonzero()
for epoch in xrange(n_epochs):
    for u, i in zip(users,items):
        e = R[u, i] - prediction(P[:,u],Q[:,i])  # Calculate error for gradient
        P[:,u] += gamma * ( e * Q[:,i] - lmbda * P[:,u]) # Update latent user feature matrix
        Q[:,i] += gamma * ( e * P[:,u] - lmbda * Q[:,i])  # Update latent movie feature matrix
    train_rmse = rmse(I,R,Q,P) # Calculate root mean squared error from train dataset
    test_rmse = rmse(I2,T,Q,P) # Calculate root mean squared error from test dataset
    train_errors.append(train_rmse)
    test_errors.append(test_rmse)

"""
Since you stored all the train_errors and test_errors for every epoch you could now plot the learning curve of SGD-WR.
"""
# Check performance by plotting train and test errors
import matplotlib.pyplot as plt
%matplotlib inline

plt.plot(range(n_epochs), train_errors, marker='o', label='Training Data');
plt.plot(range(n_epochs), test_errors, marker='v', label='Test Data');
plt.title('SGD-WR Learning Curve')
plt.xlabel('Number of Epochs');
plt.ylabel('RMSE');
plt.legend()
plt.grid()
plt.show()

"""
The model seems to perform quite well, with a relatively low RMSE after convergence. The performance can be influenced by tweaking the hyperparameters λ,γ and k.
Next you could compare the actual rating with the predicted rating. To do this you first calculate the prediction matrix – for that you can use prediction function you have implemented above and convert it to a dataframe for the ease of use.
"""
# Calculate prediction matrix R_hat (low-rank approximation for R)
R = pd.DataFrame(R)
R_hat=pd.DataFrame(prediction(P,Q))

# Compare true ratings of user 17 with predictions
ratings = pd.DataFrame(data=R.loc[16,R.loc[16,:] > 0]).head(n=5)
ratings['Prediction'] = R_hat.loc[16,R.loc[16,:] > 0]
ratings.columns = ['Actual Rating', 'Predicted Rating']
