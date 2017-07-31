/**
  * Created by huangjing on 2017/7/31.
  */

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object BehaviourOpOnPairRDDExample {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("BehaviourOpOnPairRDDExample")
    val sc = new SparkContext(sparkConf)

    // actions on a single pair RDD
    val pairRDD = sc.parallelize(List((1->2),(3->4),(3->6)))
    pairRDD.foreach(println)

    val reduceRDD = pairRDD.reduceByKey((x,y) => (x+y))
    reduceRDD.foreach(println)

    val groupRDD = pairRDD.groupByKey
    groupRDD.foreach(println)

    val combineRDD = false

    val mapRDD = pairRDD.mapValues(x => x+1)
    mapRDD.foreach(println)

    val flatMapRDD = pairRDD.flatMapValues((x => x to 5))
    flatMapRDD.foreach(println)

    val keyRDD = pairRDD.keys
    keyRDD.foreach(println)

    val valueRDD = pairRDD.values
    valueRDD.foreach(println)

    // default is ascend order, using 'false' to change the order to descend
    val sortRDD = pairRDD.sortByKey(false)
    sortRDD.foreach(println)


    // actions on two pair RDDs
    val otherRDD = sc.parallelize(List((3->9)))

    val subRDD = pairRDD.subtractByKey(otherRDD)
    subRDD.foreach(println)

    //inner join
    val joinRDD = pairRDD.join(otherRDD)
    joinRDD.foreach(println)

    val leftJoinRDD = pairRDD.leftOuterJoin(otherRDD)
    leftJoinRDD.foreach(println)

    val rightJoinRDD = pairRDD.rightOuterJoin(otherRDD)
    rightJoinRDD.foreach(println)

    val cogRDD = pairRDD.cogroup(otherRDD)
    cogRDD.foreach(println)
  }
}
