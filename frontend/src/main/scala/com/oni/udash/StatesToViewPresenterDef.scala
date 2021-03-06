package com.oni.udash

import com.oni.udash.views.BindingDemoViewPresenter
import com.oni.udash.views.ComingSoonViewPresenter
// import com.oni.udash.views.MainHomeViewPresenter
import com.oni.udash.views.DemoStylesViewPresenter
import com.oni.udash.views.ErrorViewPresenter
import com.oni.udash.views._
import com.oni.udash.views.RPCDemoViewPresenter
import com.oni.udash.views.RootViewPresenter
import com.oni.udash.views.HomeViewPresenter

import io.udash.ViewPresenter
import io.udash.ViewPresenterRegistry

class StatesToViewPresenterDef extends ViewPresenterRegistry[RoutingState] {
  override def matchStateToResolver(state: RoutingState): ViewPresenter[_ <: RoutingState] = state match {
    case RootState => RootViewPresenter
    case IndexState => IndexViewPresenter
    case IndexOoState => IndexOoViewPresenter
    case HomeState => HomeViewPresenter()
    case ComingSoonState => ComingSoonViewPresenter()
    case NewSqState => NewSqViewPresenter()
    case SqDetailsState => SqDetailsViewPresenter()
    case BindingDemoState(urlArg) => BindingDemoViewPresenter(urlArg)
    case RPCDemoState => RPCDemoViewPresenter
    case DemoStylesState => DemoStylesViewPresenter
    case _ => ErrorViewPresenter
  }
}