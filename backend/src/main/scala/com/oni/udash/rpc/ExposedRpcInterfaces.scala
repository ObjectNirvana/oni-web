package com.oni.udash.rpc

import io.udash.rpc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.oni.db.SqApp
import com.oni.web.dom.Sq
import akka.actor.ActorRef

class ExposedRpcInterfaces(qualitiesService: ActorRef)(implicit clientId: ClientId) extends MainServerRPC {
  override def hello(name: String): Future[String] = {
    println("new code")
    Future.successful(s"Hello, $name!")
  }

  override def pushMe(): Unit = {
    println("on the server")
    ClientRPC(clientId).push2(42)
  }

  override def saveDetails(id: String, det: String): Unit = {
    println(s"updating $det")
    SqApp.update(Sq(id, "", Some(det)))
  }

  override def save(q: String): Unit = {
    println(s"save on the server $q")
    qualitiesService ! q
    SqApp.add(q)
    ClientRPC(clientId).updateList(List("one"))
    // ClientRPC(clientId).push(42)
  }

  override def getSqDetails(id: String): Future[Sq] = {
    println(s"getting $id")
    val sq = SqApp.findById(id)
    println(s"got $sq")
    Future( sq )
  }

  var num = 0

  override def getList(): Future[List[Sq]] = {
    println("get list on the server")
//    ClientRPC(clientId).updateList(List("abc", "def"))
//    println("2 get list on the server")
    num += 1
    //Future(List("a1-" + num, "a2- " + num))
    Future( SqApp.getAll )
  }
}
