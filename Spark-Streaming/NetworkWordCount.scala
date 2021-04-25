package org.sparkapp.mysparkapp

import org.apache.spark.{SparkConf}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{StreamingContext , Seconds}

// @author : Mohamed Adel Hassan

object NetworkWordCount {
  
  def main(args:Array[String]) :Unit = 
  {
    
    // this is my first spark-streaming program
    
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Network_WordCount_App")
    
    // Create the context with a 1 second batch size
    val ssc = new StreamingContext(sparkConf , Seconds(10))
    
    // connect to socket in (localhost:9999) to consume data from it 
    val lines = ssc.socketTextStream("127.0.0.1", 9999, StorageLevel.MEMORY_AND_DISK_SER)
    
   /* flatMap is a one-to-many DStream operation 
    * that creates a new DStream by generating multiple new records from each record in the source DStream. 
    * In this case, each line will be split into multiple words and the stream of words is represented as the words DStream.
    *  Next, we want to count these words. 
   */
    val words = lines.flatMap(_.split(" "))
    
    // counts DStream
    val wordCounts = words.filter(!_.isEmpty()).map(word => (word , 1)).reduceByKey(_+_)
    
    wordCounts.print()
    
    ssc.start()   // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate
   

  }
   
}
