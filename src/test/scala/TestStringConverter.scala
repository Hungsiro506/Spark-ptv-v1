/**
  * Created by vdh on 25/01/2017.
  */

import org.apache.spark.sql._
import com.ftel.paytv.utils.convertors._
import org.apache.spark.rdd.RDD

class TestStringConverter {

}
object TestStringConverter{
  private val appName= "Box usage analytic"
  private val master ="local"
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master(master)
                                      .appName(appName)
                                      .getOrCreate()

    val dateTimeRDD =  spark.sparkContext.parallelize( Seq("2017-01-05T23:00:00+07:00","2017-01-05T23:04:47+07:00"))
    val dateTimeInTimeStampFormatRDD: RDD[TimeStampsFormat] = dateTimeRDD.map({
      record =>
                TimeStampsFormat(StringConverter.stringToSqlTimeStamp(record))
    })
    import spark.implicits._
    val dateTimeDataFrame = dateTimeInTimeStampFormatRDD.toDF()
    dateTimeDataFrame.show()
  }
}
case class TimeStampsFormat (
  val timeStamp : java.sql.Timestamp
                            )
