"""
The goal is to complete the rating matrix, i.e to accurately predict every user’s rating for the movies they haven’t watched yet. By predicting ratings accurately, you can suggest the most suitable movies to watch next to each user individually.
The rating matrix is your central data structure. When following the UV-decomposition approach, every rating can be considered a weighted linear combination of latent user and movie factors.
"""
import numpy as np
import pandas as pd
header = ['user_id', 'item_id', 'rating', 'timestamp']
df = pd.read_csv('./ml-100k/u.data', sep='\t', names=header)
n_users = df.user_id.unique().shape[0]
n_items = df.item_id.unique().shape[0]
print 'Number of users = ' + str(n_users) + ' | Number of movies = ' + str(n_items)

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

# Calculate the RMSE
def rmse(I,R,Q,P):
    return np.sqrt(np.sum((I * (R - np.dot(P.T,Q)))**2)/len(R[R > 0]))


################################################################################
######################### Deriving the ALS Algorithm ###########################
################################################################################

"""
Building upon the intuition from the beginning of the tutorial, let’s define the model more thoroughly. Remember that you are trying to estimate two unknown matrices which, when multiplied together, yield the rating matrix. The loss function you will use is the well-known sum of squared errors.
Without the assumption of knowning one of the matrices P or Q up front, the problem is non-convex, i.e. it is not so easy to find a global optimum for your loss function. However, if you use the ALS trick to fix one of the matrices at each step, you can turn the non-convex optimisation problem into a quadratic (least squares) problem. Each step then boils down to solving an (overdetermined) system of linear equations. To find the actual update rules on how to calculate the rows of the matrices P and Q in each step, you have to find the global minimum of the loss function twice: once while considering P to be a parameter and once while considering Q to be a parameter. Luckliy you don’t need to do the math, since Zhou et al. (2008) already did it in their paper “Large-scale Parallel Collaborative Filtering for the Netflix Prize” (with a slightly different regularization term).
"""

################################################################################
###################### Implementing the ALS Algorithm ##########################
################################################################################

"""
Let’s start by defining the hyperparameters λ (regularisation weight) and k (dimensionality of the latent feature space) and initialising the latent factor matrices P and Q as well as the identity matrix E:
"""
lmbda = 0.1 # Regularisation weight
k = 20 # Dimensionality of latent feature space
m, n = R.shape # Number of users and items
n_epochs = 15 # Number of epochs

P = 3 * np.random.rand(k,m) # Latent user feature matrix
Q = 3 * np.random.rand(k,n) # Latent movie feature matrix
Q[0,:] = R[R != 0].mean(axis=0) # Avg. rating for each movie
E = np.eye(k) # (k x k)-dimensional idendity matrix


"""
The algorithm is implemented with one inner for-loop for each parameter update step and an outer for-loop to repeat this process until convergence. For simplicity, a fixed number of iterations (epochs) is defined and convergence is analysed later by plotting the learning curves on the training and test data set. Then it’s only a matter of getting your update equations right and storing the training and test error for later analysis.
"""

train_errors = []
test_errors = []

# Repeat until convergence
for epoch in range(n_epochs):
    # Fix Q and estimate P
    for i, Ii in enumerate(I):
        nui = np.count_nonzero(Ii) # Number of items user i has rated
        if (nui == 0): nui = 1 # Be aware of zero counts!

        # Least squares solution
        Ai = np.dot(Q, np.dot(np.diag(Ii), Q.T)) + lmbda * nui * E
        Vi = np.dot(Q, np.dot(np.diag(Ii), R[i].T))
        P[:,i] = np.linalg.solve(Ai,Vi)

    # Fix P and estimate Q
    for j, Ij in enumerate(I.T):
        nmj = np.count_nonzero(Ij) # Number of users that rated item j
        if (nmj == 0): nmj = 1 # Be aware of zero counts!

        # Least squares solution
        Aj = np.dot(P, np.dot(np.diag(Ij), P.T)) + lmbda * nmj * E
        Vj = np.dot(P, np.dot(np.diag(Ij), R[:,j]))
        Q[:,j] = np.linalg.solve(Aj,Vj)

    train_rmse = rmse(I,R,Q,P)
    test_rmse = rmse(I2,T,Q,P)
    train_errors.append(train_rmse)
    test_errors.append(test_rmse)

    print("[Epoch %d/%d] train error: %f, test error: %f" \
    %(epoch+1, n_epochs, train_rmse, test_rmse))

print("Algorithm converged")

"""
After alternatingly training the parameters for 15 epochs, you can plot the learning curves to analyse the performance:
"""
# Check performance by plotting train and test errors
import matplotlib.pyplot as plt
%matplotlib inline

plt.plot(range(n_epochs), train_errors, marker='o', label='Training Data');
plt.plot(range(n_epochs), test_errors, marker='v', label='Test Data');
plt.title('ALS-WR Learning Curve')
plt.xlabel('Number of Epochs');
plt.ylabel('RMSE');
plt.legend()
plt.grid()
plt.show()

"""
As you can see, both training and test error monotonically decrease and converge over time. This means you don’t seem to overfit (in which case the training error would still decrease, while the test error would increase).
The model seems to perform quite well, with a relatively low RMSE after convergence. The performance can be influenced by tweaking the hyperparameters λ and k. In this case a manual grid search over λ led to the regularisation weight of 0.1, whilst a k of 20 was just an initial choice. Zhou et al. found a weight of 0.065 and k of 20 to yield the best performance on their data set. In the end it comes down to your task at hand, so just play around with these parameters and make sure you analyse your learning curves thoroughly.
"""

################################################################################
######################### Making Recommendations ###############################
################################################################################

# Calculate prediction matrix R_hat (low-rank approximation for R)
R_hat = pd.DataFrame(np.dot(P.T,Q))
R = pd.DataFrame(R)
# Compare true ratings of user 17 with predictions
ratings = pd.DataFrame(data=R.loc[16,R.loc[16,:] > 0]).head(n=5)
ratings['Prediction'] = R_hat.loc[16,R.loc[16,:] > 0]
ratings.columns = ['Actual Rating', 'Predicted Rating']
print(ratings)

predictions = R_hat.loc[16,R.loc[16,:] == 0] # Predictions for movies that the user 17 hasn't rated yet
top5 = predictions.sort_values(ascending=False).head(n=5)
recommendations = pd.DataFrame(data=top5)
recommendations.columns = ['Predicted Rating']
print(reccomendations)
