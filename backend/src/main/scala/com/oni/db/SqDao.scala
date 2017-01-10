package com.oni.db

//import com.novus.salat._
//import com.novus.salat.dao._
//import com.novus.salat.global._
import salat._
import salat.dao._
import salat.global._

import com.mongodb.casbah.Imports._
import salat.dao.SalatDAO
import com.oni.web.dom.Sq

case class MonSq(_id:ObjectId = new ObjectId, desc: String) {
  def toDom = Sq(_id.toHexString, desc)
}

object SqDao
  extends SalatDAO[MonSq, ObjectId](collection = MongoConnection()("oni_web")("sqs"))

object SqApp {
  def add(d: String) = {
    val sq = MonSq(desc = d)
    val id = SqDao.insert(sq)
    println("Inserted sq.id:" + id)
  }

  def getAll: List[Sq] = {
    SqDao.find(ref = MongoDBObject()).toList.map(_.toDom)
  }

  def main(args:Array[String]) = {
    val sq = MonSq(desc="Foo")

    val id = SqDao.insert(sq)
    println("Inserted id:" + id)

    val found = SqDao.findOne(MongoDBObject("desc" -> "Foo"))
    println("Found record for name ->Foo:" + found)

    val dbo = grater[MonSq].asDBObject(sq)
    println("Converted DBObject:" + dbo)
  }
}
