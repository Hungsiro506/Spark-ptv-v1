package com.ftel.paytv.Services.Statistics

/**
  * Created by hungdv on 02/02/2017.
  */
import org.apache.spark.rdd.RDD
import org.joda.time._
import org.json4s.jackson.JsonMethods.parse

import scala.collection.Map

case class LogParsedCalculable (
  customerId: String,
  contractId:String,
  logId: Integer,
  appName: String,
  itemId: String,
  realTimePlaying: Integer,
  unparsedSessionMainMenu: String,
  boxTime: String,
  recieved_at: DateTime,
  ip_wan: String)
{

  def jsonStringToMap(jsonStr: String) :Map[String,Any] ={

    implicit val formats = org.json4s.DefaultFormats

    parse(jsonStr).extract[Map[String,Any]]

  }
  def parserRawLogToCalcuableLog(rawLogRDD: RDD[LogParsed]): RDD[LogParsedCalculable] ={
    val jsonObjectRDD = rawLogRDD.map {
      line =>
        val mapOfRawObject: Map[String, Any] = jsonStringToMap(line)
        val fields = mapOfRawObject.get("fields").get
        val mapOfFieldsWithOutRecievedAt = fields.asInstanceOf[Map[String,Any]]


    }
  }

}

