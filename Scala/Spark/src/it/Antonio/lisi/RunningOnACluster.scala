package it.Antonio.Lisi

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec
import scala.math.sqrt


// To run on EMR successfully + output results for Star Wars:
// aws s3 cp s3://sundog-spark/MovieSimilarities1M.jar ./
// aws s3 cp s3://sundog-spark/ml-1m/movies.dat ./
// spark-submit --executor-memory 1g MovieSimilarities1M.jar 260


object RunningOnACluster {
  /** Best Practices for running on a cluster
   * In the script just use an empty SparkConf, in order to take the configuration of the cluster and not override it,
   * if the executors start falling you may nedd to adjust the memory that each executor has.
   */
 
  
  /** Our main function where the action happens */
  def main(args: Array[String]) {
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Create a SparkContext without much actual configuration
    // We want EMR's config defaults to be used.
    val conf = new SparkConf()
    conf.setAppName("RunningOnACluster")
    val sc = new SparkContext(conf)
    
    println("\nLoading movie names...")
    
    val data = sc.textFile("s3n://sundog-spark/ml-1m/ratings.dat")
    
    /** Partitioning
     * Spark isn't totally magic, you need to think about how the data is partitioned and how much it affects the 
     * performance. For example the self-join is very expensive, and spark won't distribuite it on its own.
     * So it is common to use .partitionBy() before running a large operation that benefits from partitioning like:
     * - join, grourpByKey(), cogroup(), reduceByKey(), combineByKey(), lookup(), and groupWith().
     * Those opearations will preserve your partitioning in the results too.
     * Too few partitions of the RDD won't take full advantage of the cluster, yoo many will result in too many 
     * shuffles. So being more accurate you have to at least set as many partition as many cores or executors you 
     * have, that fit with your available memory. PartitionBy(new HashPartitioner(100)) is usually a reasonable
     * place to start for large operations.
     */
    
    
    // Map ratings to key / value pairs: user ID => movie ID, rating
    val ratings = data.map(l => l.split("::")).map(l => (l(0).toInt, (l(1).toInt, l(2).toDouble))).partitionBy(new HashPartitioner(100))
    
    // Emit every movie rated together by the same user.
    // Self-join to find every combination.
    val joinedRatings = ratings.join(ratings)   
      }
    }