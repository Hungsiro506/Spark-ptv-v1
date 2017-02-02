package com.ftel.paytv.utils.datatype_validator

import com.ftel.paytv.utils.Constants

/**
  * Created by vdh on 24/01/2017.
  */
case  class StringValidator {

}
object StringValidator{

  def isEmpty(s:String) = s == null ||  s.isEmpty
  def isNumeric(s:String) : Boolean = s.forall(_.isDigit)
  def isNumericPattern(s:String) :Boolean = s.matches(Constants.NUMERIC_PATTERN)
  def isSimpleDateTimeFormat(s:String) :Boolean = matchesPattern(s,Constants.DATETIME_FORMAT)
  def isDateTimeWithTimeZoneFormat(s:String) :Boolean = matchesPattern(s,Constants.DATETIME_WITH_TIMEZONE_FORMAT)
  def matchesPattern(s:String,pattern:String) :Boolean = s.matches(pattern)

}
