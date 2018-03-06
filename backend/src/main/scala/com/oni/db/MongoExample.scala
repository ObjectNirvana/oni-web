package com.oni.db

// import scala.collection.immutable.IndexedSeq

import org.mongodb.scala._
import rx.lang.scala._ // { scala => rxScala }
import scala.concurrent.Await
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

// import rxScala._ // Implicits._

object MongoExample {

  /**
   * Run this main method to see the output of this quick example.
   *
   * @param args takes an optional single argument for the connection string
   * @throws Throwable if an operation fails
   */
  def main(args: Array[String]): Unit = {

    import ExecutionContext.global

    DurationInt
    implicit val ec: ExecutionContext = global

    val mongoClient: MongoClient = if (args.isEmpty) MongoClient() else MongoClient(args.head)

    // get handle to "mydb" database
    val database: MongoDatabase = mongoClient.getDatabase("mydb")

    // get a handle to the "test" collection
    val collection: MongoCollection[Document] = database.getCollection("sqs")

    // Now an rxObservable!
//    println("Dropping the test collection")
//    val dc: SingleObservable[Completed] = collection.drop()
//    val dropRx: SingleObservable[Completed] = dc
//    val r = Await.result(dropRx.toFuture, 30 seconds)

//    println(s"r = $r")
    // assert(r.first == Completed())

    // Insert some documents
//    println("Inserting documents")
//    val documents: IndexedSeq[Document] = (1 to 100) map { i: Int =>
//      Document("_id" -> i)
//    }
//    collection.insertMany(documents).foreach { r =>
//      // r.first
//      println(s"inserted $r")
//    }

    println("Finding documents")
    collection.find().map { c =>
      println(s"c = $c")
    }
    // assert(collection.find().toList.toBlocking.head == documents)
  }
}