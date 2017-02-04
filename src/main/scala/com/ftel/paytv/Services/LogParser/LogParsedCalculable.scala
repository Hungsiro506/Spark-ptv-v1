package com.ftel.paytv.Services.LogParser

/**
  * Created by hungdv on 02/02/2017.
  */
import com.ftel.paytv.utils.convertors.MapConverter
import org.apache.spark.rdd.RDD
import org.joda.time._
import org.json4s.jackson.JsonMethods.parse

import scala.collection.Map

@SerialVersionUID(100L)
case class LogParsedCalculable  (
                                  customerId: String,
                                  contractId:String,
                                  logId: Integer,
                                  appName: String,
                                  itemId: String,
                                  realTimePlaying: Integer,
                                  unparsedSessionMainMenu: String,
                                  boxTime: String,
                                  recieved_at: DateTime,
                                  ip_wan: String)extends Serializable
{

  def parserRawLogToCalculableLog(rawLogRDD: RDD[String]): RDD[LogParsedCalculable] ={
    val jsonObjectRDD = rawLogRDD.map {
      line =>
        val mapOfRawObject = jsonStringToMap(line)
        val fields = mapOfRawObject.get("fields").get
        val mapOfFieldsWithOutRecievedAt = fields.asInstanceOf[Map[String,Any]]
        val mapOfFields = mapOfFieldsWithOutRecievedAt + ("received_at" -> mapOfRawObject("received_at"))
        val parsedJsonObject = LogParsed.toParsedLog(mapOfFields)
        parsedJsonObject
    }
    val parsedLogCalculableRDD = jsonObjectRDD.map{
      i =>
        LogParsedCalculable(i.customerId,i.contract,LogUtils.parseLogId(i.logId),
          i.appName,i.itemId,LogUtils.parseRealTimePlaying(i.realTimePlaying),
          i.sessionMainMenu,i.boxTime,stringTOJodaDateTime(i.received_at),
          i.ip_wan)
    }

  }

  def jsonStringToMap(jsonStr: String) :Map[String,Any] ={

    implicit val formats = org.json4s.DefaultFormats

    parse(jsonStr).extract[Map[String,Any]]

  }

}

@SerialVersionUID(100L)
case class LogParsed (
                       customerId: String,
                       contract: String,
                       logId: String,
                       appName: String,
                       itemId: String,
                       realTimePlaying: String,
                       sessionMainMenu: String,
                       boxTime: String,
                       received_at: String,
                       sessionSubMenu: String,
                       ip_wan: String,
                       firmware: String,
                       screen: String,
                       duration: String)extends Serializable
{

}

object LogParsed{
  def toParsedLog(mapPropertiesOfJsonClass :Map[String,Any]): LogParsed={

    val customerId      = MapConverter.safetyGetValueByKey("CustomerID",mapPropertiesOfJsonClass)
    val contract        = MapConverter.safetyGetValueByKey("Contract",mapPropertiesOfJsonClass)
    val logId           = MapConverter.safetyGetValueByKey("LogId",mapPropertiesOfJsonClass)
    val appName         = MapConverter.safetyGetValueByKey("AppName",mapPropertiesOfJsonClass)
    val itemId          = MapConverter.safetyGetValueByKey("ItemId",mapPropertiesOfJsonClass)
    val realTimePlaying = MapConverter.safetyGetValueByKey("RealTimePlaying",mapPropertiesOfJsonClass)
    val sessionMainMenu = MapConverter.safetyGetValueByKey("SessionMainMenu",mapPropertiesOfJsonClass)
    val boxTime         = MapConverter.safetyGetValueByKey("BoxTime",mapPropertiesOfJsonClass)
    val ip_wan          = MapConverter.safetyGetValueByKey("ip_wan",mapPropertiesOfJsonClass)
    val firmware        = MapConverter.safetyGetValueByKey("Firmware",mapPropertiesOfJsonClass)
    val sessionSubMenu  = MapConverter.safetyGetValueByKey("SessionSubMenu",mapPropertiesOfJsonClass)
    val screen          = MapConverter.safetyGetValueByKey("Screen",mapPropertiesOfJsonClass)
    val duration        = MapConverter.safetyGetValueByKey("Duration",mapPropertiesOfJsonClass)
    val received_at     = MapConverter.safetyGetValueByKey("received_at",mapPropertiesOfJsonClass)

    LogParsed(customerId,
      contract,
      logId,
      appName,
      itemId,
      realTimePlaying,
      sessionMainMenu,
      boxTime,
      received_at,
      sessionSubMenu,
      ip_wan,
      firmware,
      screen,
      duration)
  }
}
