package com.oni.udash

import io.udash.RoutingRegistry
import io.udash.Url
import io.udash._
import io.udash.utils.Bidirectional

class RoutingRegistryDef extends RoutingRegistry[RoutingState] {
  override def matchUrl(url: Url): RoutingState =
    url2State.applyOrElse(url.value.stripSuffix("/"), (x: String) => ErrorState)

  override def matchState(state: RoutingState): Url =
    Url(state2Url.apply(state))

  private val (url2State, state2Url) = Bidirectional[String, RoutingState] {
    case "" => HomeState
    case "/soon" => ComingSoonState
    case "/newsq" => NewSqState
    case "/index" => IndexState
    case "/binding" => BindingDemoState("")
    case "/binding" /:/ arg => BindingDemoState(arg)
    case "/rpc" => RPCDemoState
    case "/scalacss" => DemoStylesState
  }
}