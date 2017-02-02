package com.ftel.paytv.Services.Statistics

/**
  * Created by hungdv on 02/02/2017.
  */
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}

 class LogLoader (
                   private var spark: SparkSession
                 )
 {
   def loadTextFileFromLocalFile(path: String): RDD[String]={
     return spark.sparkContext.textFile(path)
   }
 }
object LogLoader{

}


