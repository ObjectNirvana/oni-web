package com.oni.udash

import com.oni.udash.views.BindingDemoViewPresenter
import com.oni.udash.views.ComingSoonViewPresenter
import com.oni.udash.views.DemoStylesViewPresenter
import com.oni.udash.views.ErrorViewPresenter
import com.oni.udash.views.IndexViewPresenter
import com.oni.udash.views.RPCDemoViewPresenter
import com.oni.udash.views.RootViewPresenter

import io.udash.ViewPresenter
import io.udash.ViewPresenterRegistry

class StatesToViewPresenterDef extends ViewPresenterRegistry[RoutingState] {
  override def matchStateToResolver(state: RoutingState): ViewPresenter[_ <: RoutingState] = state match {
    case RootState => RootViewPresenter
    case IndexState => IndexViewPresenter
    case ComingSoonState => ComingSoonViewPresenter
    case BindingDemoState(urlArg) => BindingDemoViewPresenter(urlArg)
    case RPCDemoState => RPCDemoViewPresenter
    case DemoStylesState => DemoStylesViewPresenter
    case _ => ErrorViewPresenter
  }
}