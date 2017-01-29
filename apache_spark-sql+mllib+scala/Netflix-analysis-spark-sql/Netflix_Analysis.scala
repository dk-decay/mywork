/*
@author - Devesh Kandpal
Project - Netflix_2011_2016.csv file is read and using Spark SQL
I am annswering questions provided in the comments.

Run - start spark shell where Netflix_Analysis.scala is kept and
run => :load Netflix_Analysis.scala

*/ 

import org.apache.spark.sql.SparkSession



val spark = SparkSession.builder().getOrCreate()
// Load the Netflix Stock CSV File, have Spark infer the data types.
val df = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")
// What are the column names?
val cols = df.columns
// What does the Schema look like?
df.printSchema()
// Print out the first 5 columns.
for(col <- 0 until 5) {
	println(cols(col))
}
// Use describe() to learn about the DataFrame.
df.describe().show()
// Create a new dataframe with a column called HV Ratio that
// is the ratio of the High Price versus volume of stock traded
// for a day.
val df2 = df.withColumn("HV Ratio", (df("High") / df("Volume")))
df2.show()
// What day had the Peak High in Price
val maxHigh = df2.select(max("High")).collect()(0).getDouble(0)

df2.select($"Date", $"High").filter($"High" === maxHigh).select(dayofmonth($"Date")).show()
// What is the mean of the Close column
df2.select(mean($"Close")).show()
// What is the max and min of the Volume column
df2.select(max($"Volume"), min($"Volume")).show()

// How many days was the Close lower than $ 600?
df2.filter($"Close" < 600).count()
// What percentage of the time was the High greater than $500 
(100 * 1.0 * df2.filter($"High" > 500).count()) / df2.count
// What is the Pearson correlation between High and Volume
df2.select(corr($"High", $"Volume")).show()
// What is the max High per year
val df3 = df2.withColumn("Year",year($"Date"))
df3.groupBy("Year").max().select($"Year", $"max(High)").orderBy($"Year").show()
// What is the average Close for each Calender Month

val df4 = df3.withColumn("Month", month($"Date"))
df4.groupBy("Month").mean().select($"Month", $"avg(Close)").show()
