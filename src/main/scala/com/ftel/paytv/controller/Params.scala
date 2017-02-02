package com.ftel.paytv.controller

/**
  * Created by vdh on 25/01/2017.
  *
  */
/** Base trait for all kinds of parameters that will be passed to constructors
  * of different controller classes.
  *
  * @group Helper
  */
trait Params extends Serializable{}

/** A concrete implementation of [[Params]] representing empty parameters.
  *
  * @group Helper
  */
case class EmptyParams() extends Params {
  override def toString(): String = "Empty"
}
