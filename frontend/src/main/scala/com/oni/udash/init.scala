package com.oni.udash

import io.udash._
import io.udash.wrappers.jquery._
import org.scalajs.dom.{ Element, document }

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import com.oni.udash.styles.OniStyles
import scalacss.Defaults._
import scalacss.ScalatagsCss._
import scalatags.JsDom._
import com.oni.udash.styles.GlobalStyles
import com.oni.udash.styles.DemoStyles
import com.oni.udash.styles.partials.FooterStyles
import com.oni.udash.styles.partials.HeaderStyles
import org.scalajs.dom.raw.HTMLStyleElement

object Context {
  implicit val executionContext = scalajs.concurrent.JSExecutionContext.Implicits.queue
  private val routingRegistry = new RoutingRegistryDef
  private val viewPresenterRegistry = new StatesToViewPresenterDef

  implicit val applicationInstance = new Application[RoutingState](routingRegistry, viewPresenterRegistry, RootState)

  import io.udash.rpc._
  import com.oni.udash.rpc._
  val serverRpc = DefaultServerRPC[MainClientRPC, MainServerRPC](new RPCService)

}

object Init extends JSApp with StrictLogging {
  import Context._

  @JSExport
  override def main(): Unit = {
    jQ(document).ready((_: Element) => {
      val appRoot = jQ("#application").get(0)
      if (appRoot.isEmpty) {
        logger.error("Application root element not found! Check your index.html file!")
      } else {
        applicationInstance.run(appRoot.get)

        jQ(GlobalStyles.render[TypedTag[org.scalajs.dom.raw.HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(DemoStyles.render[TypedTag[org.scalajs.dom.raw.HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(OniStyles.render[TypedTag[org.scalajs.dom.raw.HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(FooterStyles.render[TypedTag[org.scalajs.dom.raw.HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(HeaderStyles.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
      }
    })
  }
}