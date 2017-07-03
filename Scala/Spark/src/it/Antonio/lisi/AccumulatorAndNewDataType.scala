package it.Antonio.Lisi

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.spark.util.LongAccumulator
import org.apache.log4j._
import scala.collection.mutable.ArrayBuffer

/** Accumulator
 * An accumulator allows many executors to increment a shared variable. A LongAccumulator is a low-level collection
 * specialized for gathering elements in parallel and then joining them in order to merging them. It is the same for
 * the DoubleAccumulator, the difference is that while in the first case the shared variable is a Long Integer Value,
 * in the second case it is a Double variable.  
 */
object AccumulatorAndNewDataType {
  def main(args: Array[String]) {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val sc = new SparkContext("local", "AccumulatorAndNewDataType")
  var df = sc.parallelize(List(1,23,4,5,6,7,9))
  var hitCounter:Option[LongAccumulator] = None 
  hitCounter = Some(sc.longAccumulator("Hit Counter"))
  /** Custom Data Type
   *  We can create custom data type in order to make the code more fluid.
  */
  type IntInt = (Int, Int)
  var oneTwo: IntInt =  (1,2)
  while (hitCounter.get.value < 10) {
    for (i <- df.take(3)) {
      hitCounter.get.add(i)
    }
  }
  if (hitCounter.isDefined) {
        val hitCount = hitCounter.get.value
        if (hitCount > 10) {
          println("We did it " + hitCount)
        }
      }
  }
}
 