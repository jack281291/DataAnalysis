package it.Antonio.Lisi

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._


object RDDBasic {
 
  /** Our main function where the action happens */
  def main(args: Array[String]) {
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////// TRANSFORMATIONS /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
        
    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "RatingsCounter")
   
    // Load up each line of the ratings data into an RDD
    val lines = sc.textFile("../Spark/ml-100k/u.data")
    
    // Convert each line to a string, split it out by tabs, and extract the third field.
    // (The file format is userID, movieID, rating, timestamp)
    val ratings = lines.map(x => x.toString().split("\t")(2))
    
    
    
    // Count up how many times each value (rating) occurs
    val results = ratings.countByValue() // it returns a tuple (id, count) for every unique id
    
    // Sort the resulting map of (rating, count) tuples
    val sortedResults = results.toList.sortWith(_._2 > _._2)
    val firstOne = sortedResults(0)
    // Print each result on its own line.
    sortedResults.foreach(println)
    
    // filter()
    
    val onlyOneRating = ratings.filter( x => x.toInt == 1)
    val howManyOnes = onlyOneRating.countByValue()
    println(s"Ones = $howManyOnes")
    
    // distinct()
    val distinctRatings = ratings.distinct()
    val distinctRatingsToPrint = distinctRatings.collect()
    println(s"Distinct Ratings = $distinctRatingsToPrint")
    
    // sample()
    val sampleRatings = ratings.sample(false, 0.001, 101)
    val sampleRatingsToPrint = sampleRatings.collect().foreach(println)
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// ACTIONS /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    // count
    val numberOfRatings = ratings.count()
    println(s"Number of ratings = $numberOfRatings")
    
    // countByValue()
    // Count up how many times each value (rating) occurs
    val resultsAgain = ratings.countByValue() // it returns a tuple (id, count) for every unique id
    
    // take
    val firstFiveRatings = ratings.take(5)
    
    // reduce
    val ratingsMapped = ratings.map( _.toInt )
    val resultNew = ratingsMapped.reduce( _ + _ )
    val Mean = resultNew.toDouble / numberOfRatings.toDouble
    println(s"Ratings Mean = $Mean")  
  }
}
