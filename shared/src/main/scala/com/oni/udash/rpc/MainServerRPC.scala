package com.oni.udash.rpc

import com.avsystem.commons.rpc.RPC
import io.udash.rpc._
import scala.concurrent.Future
import com.oni.web.dom._

@RPC
trait MainServerRPC {
  def hello(name: String): Future[String]
  def pushMe(): Unit
  def getList(): Future[List[Sq]]
  def getSqDetails(id: String): Future[Sq]
  def save(newQuality: String): Unit
  def saveDetails(id: String, det: String): Unit
}
