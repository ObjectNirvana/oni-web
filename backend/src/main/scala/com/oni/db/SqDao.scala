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

case class MonSq(_id:ObjectId = new ObjectId,
    desc: String,
    det: String = "",
    details: Option[String] = None) {
  def toDom = Sq(_id.toString, desc, details)
  def toDomSmall = Sq(_id.toString, desc)
}

object MonSq {
  def apply(sq: Sq): MonSq = MonSq(new ObjectId(sq.id), sq.desc, sq.details.getOrElse(""), sq.details)
  
}

trait SqDaoComponent {
  trait SqDao {
    def add(d: String): Unit
    def findById(id: String): Sq
    def update(sq: Sq): Unit
    def getAll: List[Sq]
  }
}

trait SqDaoComponentImpl extends SqDaoComponent {
  class SqDaoImpl extends SqDao {
    private object SqDaoSalatImpl
      extends SalatDAO[MonSq, ObjectId](collection = MongoConnection()("oni_web")("sqs"))

    override def add(d: String) = {
      val sq = MonSq(desc = d)
      val id = SqDaoSalatImpl.insert(sq)
      println("Inserted sq.id:" + id)
    }
  
    override def findById(id: String): Sq = {
      val find = SqDaoSalatImpl.findOneById(new ObjectId(id))
      import scalaz._
      val memoFind = Memo
      find.get.toDom
    }
  
    override def update(sq: Sq): Unit = {
      try {
        //SqDao.save(MonSq(sq))
        SqDaoSalatImpl.findOneById(new ObjectId(sq.id)) match {
          case Some(obj) =>
            println("got obj")
            val mod = obj.copy(det = sq.details.getOrElse("x"),
                  details = sq.details)
            SqDaoSalatImpl.save(mod)
            val dbo = grater[MonSq].asDBObject(mod)
            println(s"updating to $dbo")
            val r = SqDaoSalatImpl.update(MongoDBObject("_id" -> sq.id), dbo, false, false)
            println(s"r = $r")
            val db1 = SqDaoSalatImpl.findOneById(new ObjectId(sq.id))
            println(s"db1 = $db1")
          case None =>
            println(s"id not found ${sq.id}")
        }
      } catch {
        case t: Throwable =>
          t.printStackTrace()
      }
    }
  
    override def getAll: List[Sq] = {
      SqDaoSalatImpl.find(ref = MongoDBObject()).toList.map(_.toDomSmall)
    }
  }
}

trait SqServiceComponentImpl {
  this: SqDaoComponent =>

  val sqDao: SqDao

  class SqServiceImpl {
    
  }
}

object ComponentRegistry
    extends SqDaoComponentImpl
    with SqServiceComponentImpl {

  override val sqDao = new SqDaoImpl

  val SqService = new SqServiceImpl
}

object SqAppO {
  object SqDao
    extends SalatDAO[MonSq, ObjectId](collection = MongoConnection()("oni_web")("sqs"))

  def add(d: String) = {
    val sq = MonSq(desc = d)
    val id = SqDao.insert(sq)
    println("Inserted sq.id:" + id)
  }

  def findById(id: String): Sq = {
    SqDao.findOneById(new ObjectId(id)).get.toDom
  }

  def update(sq: Sq): Unit = {
    try {
      //SqDao.save(MonSq(sq))
      SqDao.findOneById(new ObjectId(sq.id)) match {
        case Some(obj) =>
          println("got obj")
          val mod = obj.copy(det = sq.details.getOrElse("x"),
                details = sq.details)
          SqDao.save(mod)
          val dbo = grater[MonSq].asDBObject(mod)
          println(s"updating to $dbo")
          val r = SqDao.update(MongoDBObject("_id" -> sq.id), dbo, false, false)
          println(s"r = $r")
          val db1 = SqDao.findOneById(new ObjectId(sq.id))
          println(s"db1 = $db1")
        case None =>
          println(s"id not found ${sq.id}")
      }
    } catch {
      case t: Throwable =>
        t.printStackTrace()
    }
  }

  def getAll: List[Sq] = {
    SqDao.find(ref = MongoDBObject()).toList.map(_.toDomSmall)
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
