/*
@author - Devesh Kandpal
Project - This program loads a sample dataset from Kagal
which comprises of passenger information from titanic
and using categorical data apart from numerical data,
we are able to predict if the passenger survived or not.
Logistic Regression is used as the ML algorithm
*/

import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.{VectorAssembler,StringIndexer,VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics


Logger.getLogger("org").setLevel(Level.ERROR)


val spark = SparkSession.builder().getOrCreate()

val data = (spark
	.read
	.option("header","true")
	.option("inferSchema","true")
	.format("csv")
	.load("titanic.csv"))

data.printSchema()

val columns = data.columns
val firstRow = data.head(1)(0)

for(i <- Range(1, columns.length)) {
println(columns(i))
println(firstRow(i))
println("\n")
}

val logRegDataAll = (data.select(
					  $"Survived".as("label"),
					  $"Pclass",
					  $"Name",
					  $"Sex",
					  $"Age",
					  $"SibSp",
					  $"Parch",
					  $"Fare",
					  $"Embarked"))


val logRegData = logRegDataAll.na.drop()


//Converting strings to numerical values
val genderIndexer = new StringIndexer().setInputCol("Sex").setOutputCol("SexIndex")
val embarkedIndexer = new StringIndexer().setInputCol("Embarked").setOutputCol("EmbarkedIndex")


// Convert numerical values to one hot encoding ie.. 1 or 0

val genderEncoder = new OneHotEncoder().setInputCol("SexIndex").setOutputCol("SexVector")
val embarkedEncoder = new OneHotEncoder().setInputCol("EmbarkedIndex").setOutputCol("EmbarkedVector")


// (label, features), convert input cols to features array
val assembler = (new VectorAssembler()
	.setInputCols(Array("Pclass",
		"SexVector","Age","SibSp",
		"Parch","Fare","EmbarkedVector"))
	.setOutputCol("features"))


// split total data to training and test data in the ratio of 70% to 30%
val Array(training, test) = logRegData.randomSplit(Array(0.7, 0.3), seed=12345)

val lr = new LogisticRegression()

// executes each step sequentially as defined in the pipeline
val pipeline = new Pipeline().setStages(Array(genderIndexer,embarkedIndexer,genderEncoder,embarkedEncoder,assembler,lr))

// prepares the model
val model = pipeline.fit(training)
// it will test the model on the test data
val results = model.transform(test)

// model evaluation

val predictionsAndLabels = results.select($"label",$"prediction").as[(Double,Double)].rdd

val metrics = new MulticlassMetrics(predictionsAndLabels)

println("Confusion Matrix :")
println(metrics.confusionMatrix)
