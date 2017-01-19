package com.oni.udash

import acyclic.file

import io.udash._
import io.udash.wrappers.jquery._
import org.scalajs.dom.{ Element, document }

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scalacss.Defaults._
import scalacss.ScalatagsCss._
import scalatags.JsDom._
import com.oni.udash.styles._
import com.oni.udash.styles.OniStyles2
import com.oni.udash.styles.GlobalStyles
import com.oni.udash.styles.DemoStyles
import com.oni.udash.styles.partials.FooterStyles
import com.oni.udash.styles.partials.HeaderStyles
import org.scalajs.dom.raw.HTMLStyleElement

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

        jQ(GlobalStyles.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(DemoStyles.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(OniStyles.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(OniStyles2.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(CsStyles.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(CsStyles2.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(FooterStyles.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
        jQ(HeaderStyles.render[TypedTag[HTMLStyleElement]].render).insertBefore(appRoot.get)
      }
    })
  }
}
