package org.sparkapp.mysparkapp

import org.apache.spark.sql.SparkSession

object sparkSqlJson {
  
  def main(args : Array[String]) : Unit = 
  {
    
    val sparkSession = SparkSession
    .builder()
    .appName("SparkSql_Json")
    .master("local")
    .getOrCreate()
    
    
    // put your dataset into hadoop file system
   val _LOCATION = "hdfs://localhost:9000/Datasets/tweets.json"
   
   // spark sql automatically can infer json schema by using this line
   val tweets_df = sparkSession.read.json(_LOCATION)
   
   println("----------------------------- print df schema --------------------------------------------------")
   tweets_df.printSchema()
   
   /* 
     country: string (nullable = true)
     |-- id: string (nullable = true)
     |-- place: string (nullable = true)
     |-- text: string (nullable = true)
     |-- user: string (nullable = true)
     
    */
   
   println("----------------------------- print tweets df --------------------------------------------------")
   tweets_df.show()
   
   // create a temporary table
   
   tweets_df.createOrReplaceTempView("tweets_tbl")
   
   sparkSession.sql("Select user , text from tweets_tbl").show()
   
    // get all unique countries tweeted
   sparkSession.sql("select count(distinct country) as unique_country from tweets_tbl").show()
   
    // get top 10 country tweets
   sparkSession.sql("select country  , count(country) as count_country from tweets_tbl group by country order by count_country DESC limit 10").show()
    
    // listing all temporary tables in spark catalog
   sparkSession.catalog.listTables().foreach(table=> println(table.toString()))
