package com.oni.udash.rpc

import io.udash.rpc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ExposedRpcInterfaces(implicit clientId: ClientId) extends MainServerRPC {
  override def hello(name: String): Future[String] = {
    println("new code")
    Future.successful(s"Hello, $name!")
  }

  override def pushMe(): Unit =
    ClientRPC(clientId).push(42)
}

       