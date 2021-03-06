package com.oni.udash

import io.udash.Application
import io.udash.State

sealed abstract class RoutingState(val parentState: RoutingState) extends State {
  def url(implicit application: Application[RoutingState]): String = s"#${application.matchState(this).value}"
}

case object RootState extends RoutingState(null)

case object ErrorState extends RoutingState(RootState)

case object IndexState extends RoutingState(RootState)
case object IndexOoState extends RoutingState(RootState)
case object HomeState extends RoutingState(RootState)

case object ComingSoonState extends RoutingState(RootState)
case object NewSqState extends RoutingState(RootState)
case object SqDetailsState extends RoutingState(RootState)

case class BindingDemoState(urlArg: String = "") extends RoutingState(RootState)

case object RPCDemoState extends RoutingState(RootState)

case object DemoStylesState extends RoutingState(RootState)
