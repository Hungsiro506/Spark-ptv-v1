package com.ftel.paytv.core

import com.ftel.paytv.common.annotation.DeveloperApi
import com.ftel.paytv.controller.Params

/**
  * Created by vdh on 25/01/2017.
  */
@DeveloperApi
abstract class AbstractDoer extends Serializable {
/** :: DeveloperApi ::
* Provides facility to instantiate controller classes
*
* */

}
@DeveloperApi
object Doer {
  /** :: DeveloperApi ::
    * Instantiates a controller class using supplied controller parameters as
    * constructor parameters
    *
    * @param cls Class of the controller
    * @param params Parameters of ther controller class
    * @tparam C Controller class
    * @return An instance of the controller class
    *
    */
  @DeveloperApi
  def apply[C <: AbstractDoer](cls: Class[_ <: C], params : Params): C = {
      try{
        val constr = cls.getConstructor(params.getClass)
        constr.newInstance(params)
      }catch{
        case e: NoSuchMethodException =>try{
          val zeroConstr = cls.getConstructor()
          zeroConstr.newInstance()
        }catch{
          case e: NoSuchMethodException =>
            error(s"${params.getClass.getName} was used as the constructor " +
            s"argument to ${e.getMessage}, but no constructor can handle it." +
            "Aborting.")
            sys.exit(1)
        }
      }
  }
}
