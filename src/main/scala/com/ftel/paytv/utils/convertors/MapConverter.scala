package com.ftel.paytv.utils.convertors

/**
  * Created by vdh on 25/01/2017.
  */
class MapConverter {

}
object MapConverter{
  def getValueByKeyFromMap(s :String,map: scala.collection.Map[String,Any]): Option[Any] ={
    try{
      Some(map(s))
    }catch{
      case e:NoSuchElementException => None
    }
  }
  def safetyGetValueByKey(s:String,map: scala.collection.Map[String,Any])={
    getValueByKeyFromMap(s,map) match{
      case Some(n) => n.toString
      case None    => null
    }
  }
}
