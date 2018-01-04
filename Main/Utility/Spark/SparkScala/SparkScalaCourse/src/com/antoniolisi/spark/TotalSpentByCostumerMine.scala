package com.antoniolisi.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._



object TotalSpentByCostumerMine{
  
  def parseLine(line:String)= {
    val fields = line.split(",")
    val customer_id = fields(0)
    val amount_spent = fields(2).toFloat
    (customer_id, amount_spent)
  }
    /** Our main function where the action happens */
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "TotalSpentByCostumerMine")
    
    // Read each line of input data
    val lines = sc.textFile("../customer-orders.csv")
    
    // Convert to (customer_id, amount_spent) tuples
    val parsedLines = lines.map(parseLine)
    
    val amountSpentByCustomer = parsedLines.reduceByKey( (x,y) => x + y)
    
    val amountSpentByCustomer_ = amountSpentByCustomer.map(x => (x._2, x._1))
    
    val results = amountSpentByCustomer_.collect()
    
    for (result <- results.sorted) {
       val id = result._2
       val amount = result._1
       println(s"$id Total amount spent: $amount") 
    }
  }
}