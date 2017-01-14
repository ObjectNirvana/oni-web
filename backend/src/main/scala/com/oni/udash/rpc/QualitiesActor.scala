package com.oni.udash.rpc

import akka.actor.Actor
import akka.actor.ActorLogging

class QualitiesActor extends Actor with ActorLogging {

  override def receive: Actor.Receive = {
    case newQ: String =>
      log.info(s"new sq $newQ")
      println(s"new sq $newQ")
    case x =>
      log.error("unknown message: " + x)
      sender ! None
  }

}