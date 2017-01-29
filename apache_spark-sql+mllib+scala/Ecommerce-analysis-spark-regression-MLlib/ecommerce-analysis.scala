/**
author - Devesh Kandpal
This application reads an ecommerce-customer data file
comprising of various parameters that tells us how much
time the user spends on that ecommerce site and how much
money the user spends on that ecommerce app. We use
Linear Regression from Spark MLib to create a model
with known features and we predict the price that the user
is likely to spend on basis of these features.
**/

import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}
import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

Logger.getLogger("org").setLevel(Level.ERROR)


val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header","true").option("inferSchema","true").format("csv").load("Ecommerce-Customers")
df.printSchema()

val colNames = df.columns
val firstRow = df.head(1)(0)
println("Example row");
for(ind <- Range(1,colNames.length)){
  println(colNames(ind))
  println(firstRow(ind))
  println("\n")
}

val formattedDf = (df.select($"Yearly Amount Spent".as("label"),
					$"Avg Session Length",
					$"Time on App",
					$"Time on Website",
					$"Length of Membership"))


val assembler = (new VectorAssembler()
	.setInputCols(Array(
		"Avg Session Length",
		"Time on App",
		"Time on Website",
		"Length of Membership"))
	.setOutputCol("features"))

val output = assembler.transform(formattedDf).select($"label",$"features")

val lr = new LinearRegression()
val lrModel = lr.fit(output)
val trainingSummary = lrModel.summary

println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")
println(s"numIterations: ${trainingSummary.totalIterations}")
println(s"objectiveHistory: ${trainingSummary.objectiveHistory.toList}")



println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
println(s"MSE: ${trainingSummary.meanSquaredError}")
println(s"r2: ${trainingSummary.r2}")


trainingSummary.predictions.show
trainingSummary.residuals.show

