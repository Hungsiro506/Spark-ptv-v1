package com.ftel.paytv.utils
import java.sql.Timestamp
/**
  * Created by vdh on 25/01/2017.
  */
object untils {
  def safetyCheckEmpty(x:Any): Boolean={
    x match{
      case null => false
      case false => false
      case s:String if s.isEmpty => false
      case _ => true
    }
  }
/*  def calculateVectorHourly(realTimePlaying : Int, received_at : Timestamp): ArrayBuffer[Int]={
    val vectorHouly = collection.mutable.ArrayBuffer.fill(24)(0)
    var currentTime = received_at.getMinutes*60 + received_at.getSeconds
    if(currentTime > realTimePlaying) currentTime = realTimePlaying
    val currentHour = received_at.getHours
    vectorHouly(currentHour) = currentTime
    var beforeTime = realTimePlaying - currentTime
    if(beforeTime > 0){
      val stopTime = received_at.
    }
  }*/
}
