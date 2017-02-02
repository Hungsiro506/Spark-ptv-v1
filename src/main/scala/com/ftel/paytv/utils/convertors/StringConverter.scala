package com.ftel.paytv.utils.convertors

import java.security.MessageDigest
import java.sql.{Date, Timestamp}
import java.text.SimpleDateFormat

import com.ftel.paytv.utils.Constants
import com.ftel.paytv.utils.datatype_validator.StringValidator
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.ftel.paytv.common.annotation.DeveloperApi

/**
  * Created by vdh on 24/01/2017.
  */
class StringConverter {


}

object StringConverter{

  /** :: DeveloperAPI ::
    * Convert String to Joda Datetime
    *@param s Input string
    *@return Joda datetime instance.
    */
  @DeveloperApi
  def stringToJodaDateTime(s :String): DateTime = {
    if(StringValidator.isEmpty(s) ) return null
    //if(StringValidator.isEmpty(s) || !StringValidator.isSimpleDateTimeFormat(s)) return null
    else{
      val format = new java.text.SimpleDateFormat(Constants.DATETIME_FORMAT)
      val date = format.parse(s)
      return new DateTime(date)
    }
  }

  /** :: DeveloperAPI ::
    * Convert String to Sql Date
    * java sql date time has three main types of datetime
    *@param s Input string
    *@return java.sql.Date datetime instance.
    */
  @DeveloperApi
  def stringToSqlDate(s: String) :java.sql.Date ={
    if(StringValidator.isEmpty(s) ) return null
    //if(StringValidator.isEmpty(s) || !StringValidator.isSimpleDateTimeFormat(s)) return null
    else{
      val formater = new SimpleDateFormat(Constants.DATETIME_FORMAT)
      val date = formater.parse(s)
      val sqlDate = new Date(date.getYear,date.getMonth,date.getDay)
      return sqlDate
    }
  }

  /** :: DeveloperAPI ::
    * Convert String to Sql TimeStamp
    * java sql date time has three main types of datetime
    *@param s Input string
    *@return java.sql.TimeStamp datetime instance.
    */
  @DeveloperApi
  def stringToSqlTimeStamp(s: String) :Timestamp={
    if(StringValidator.isEmpty(s) ) return null
    //if(StringValidator.isEmpty(s) || !StringValidator.isSimpleDateTimeFormat(s)) return null
    else{
      val formater = new SimpleDateFormat(Constants.DATETIME_FORMAT)
      val date = formater.parse(s)
      val timestamp = new java.sql.Timestamp(date.getTime)
      return timestamp
    }
  }
  private def toInt(s: String): Option[Int] ={
    try { Some(s.toInt)} catch{
      case e:NumberFormatException => None
    }
  }

  /** :: DeveloperAPI ::
    * Convert String to Int (deal with exception or unparsedable case)
    * java sql date time has three main types of datetime
    *@param s Input string
    *@return java.sql.Date datetime instance.
    */
  @DeveloperApi
  def safetyParseStringToInt(s:String,valueReturnedWhenNone:Int):Int ={
    toInt(s) match{
      case Some(i) => i
      case None => valueReturnedWhenNone
    }
  }
  def toJodaDate2(s:String) = try{
    val formater = DateTimeFormat forPattern Constants.DATETIME_FORMAT
    Some(formater parseDateTime s).map(_.toString()).getOrElse(Constants.BEGINING)
  }
  catch{ case e:IllegalArgumentException => Constants.DATETIME_FORMAT}

  def getHashCode(s:String) :String ={
    try{
      var md5 = MessageDigest.getInstance("MD5")
      md5.update(s.getBytes())
      var  bytesArray = md5.digest()
      val stringBuffer = new StringBuffer()
      var i =  0
      for(i <- 1  until bytesArray.length){
        stringBuffer.append(Integer.toString((bytesArray(i) & 0xff) + 0x100,16).substring(1))
      }
      return stringBuffer.toString()
    }
  }
  def jsonStringToMap(jsonString :String): Map[String,Any] = {

    implicit val formats = DefaultFormats
    // implicit val formats = org.json4s.DefaultFormats
    parse(jsonString,useBigDecimalForDouble = true).extract[Map[String,Any]]
  }

}
object testStringConverter{
  def main(args: Array[String]): Unit = {
    val s = StringConverter.getHashCode("Vu Duy Hung")
    println(s)
    val t = StringConverter.stringToSqlTimeStamp("2017-01-05T23:00:00+07:00")
    println(t)
  }
}
