package com.oni.udash

import io.udash.Application

object Context {
  implicit val executionContext = scalajs.concurrent.JSExecutionContext.Implicits.queue
  private val routingRegistry = new RoutingRegistryDef
  private val viewPresenterRegistry = new StatesToViewPresenterDef

  implicit val applicationInstance = new Application[RoutingState](routingRegistry, viewPresenterRegistry, RootState)

  import com.oni.udash.rpc._
  import io.udash.rpc._
  val serverRpc = DefaultServerRPC[MainClientRPC, MainServerRPC](new RPCService)

}
