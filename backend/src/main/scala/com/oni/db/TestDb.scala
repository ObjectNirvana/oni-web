package com.oni.db

import scala.collection.immutable.IndexedSeq

//import org.mongodb.scala._
//import rx.lang.{ scala => rx }
//import rxScala.Implicits._
//
///**
// * The RxScala Example usage
// */
//object TestDb {
//
//  /**
//   * Run this main method to see the output of this quick example.
//   *
//   * @param args takes an optional single argument for the connection string
//   * @throws Throwable if an operation fails
//   */
//  def main(args: Array[String]): Unit = {
//
//    val mongoClient: MongoClient = if (args.isEmpty) MongoClient() else MongoClient(args.head)
//
//    // get handle to "mydb" database
//    val database: MongoDatabase = mongoClient.getDatabase("mydb")
//
//    // get a handle to the "test" collection
//    val collection: MongoCollection[Document] = database.getCollection("test")
//
//    // Now an rxObservable!
//    println("Dropping the test collection")
//    val dropRx: rx.Observable[Completed] = collection.drop()
//    assert(dropRx.toBlocking.first == Completed())
//
//    // Insert some documents
//    println("Inserting documents")
//    val documents: IndexedSeq[Document] = (1 to 100) map { i: Int => Document("_id" -> i) }
//    collection.insertMany(documents).toBlocking.first
//
//    println("Finding documents")
//    assert(collection.find().toList.toBlocking.head == documents)
//  }
//}
