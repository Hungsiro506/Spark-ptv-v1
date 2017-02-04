package com.ftel.paytv.Services.LogParser

/**
  * Created by hungdv on 02/02/2017.
  */
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession


/*
Shoud be defined as a abtract class
Load data from source : file system, HDFS, S3, etc.
 */
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


