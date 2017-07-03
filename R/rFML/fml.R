library("BBmisc")
library("ggplot2")
library("ParamHelpers")
library("stringi")
library("mlr")

df = read.csv("/home/antonio/Dropbox/Data Analysis/python/dati/glass.data.csv")

df$Id.number = NULL

## 75% of the sample size
smp_size <- floor(0.75 * nrow(df))

## set the seed to make your partition reproductible
set.seed(101)
train_ind <- sample(seq_len(nrow(df)), size = smp_size)

train <- df[train_ind, ]
test <- df[-train_ind, ]

summarizeColumns(train)

trainTask <- makeClassifTask(data = train,target = "Type.of.glass")
testTask <- makeClassifTask(data = test, target = "Type.of.glass")

getParamSet("classif.randomForest")

#create a learner
rf <- makeLearner("classif.randomForest", predict.type = "response", par.vals = list(ntree = 200, mtry = 3))
rf$par.vals <- list(
  importance = TRUE
)


#set tunable parameters
#grid search to find hyperparameters
rf_param <- makeParamSet(
  makeIntegerParam("ntree",lower = 100, upper = 1000),
  makeIntegerParam("mtry", lower = 3, upper = 10),
  makeIntegerParam("nodesize", lower = 10, upper = 50)
)

#let's do random search for 50 iterations
rancontrol <- makeTuneControlRandom(maxit = 50L)

#set 3 fold cross validation
set_cv <- makeResampleDesc("CV",iters = 3L)

#hypertuning
rf_tune <- tuneParams(learner = rf, resampling = set_cv, task = trainTask, par.set = rf_param, control = rancontrol, measures = acc)

#cv accuracy
rf_tune$y

#best parameters
rf_tune$x

#using hyperparameters for modeling
rf.tree <- setHyperPars(rf, par.vals = rf_tune$x)

#train a model
rforest <- train(rf.tree, trainTask)
getLearnerModel(rforest)

trainTask <- makeClassifTask(data = df,target = "Type.of.glass")
rf_tune <- tuneParams(learner = rf, resampling = set_cv, task = trainTask, par.set = rf_param, control = rancontrol, measures = acc)
rf_tune$x
rf_tune$y
