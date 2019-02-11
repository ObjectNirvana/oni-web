package com.oni.udash.views

import io.udash._
import com.oni.udash._
import org.scalajs.dom.Element
import com.oni.udash.styles.{DemoStyles, GlobalStyles}
import scalacss.ScalatagsCss._

object IndexOoViewPresenter extends DefaultViewPresenterFactory[IndexOoState.type](() => new IndexOoView)

class IndexOoView extends View {
  import com.oni.udash.Context._
  import scalatags.JsDom.all._

  private val content = div(
    h2("Object Oriented Software"),
    ul(DemoStyles.stepsList)(
      li(a(DemoStyles.underlineLink, href := BindingDemoState().url)("Binding demo")),
      li(a(DemoStyles.underlineLink, href := BindingDemoState("From index").url)("Binding demo with URL argument")),
      li(a(DemoStyles.underlineLink, href := RPCDemoState.url)("RPC demo")),
      li(a(DemoStyles.underlineLink, href := DemoStylesState.url)("ScalaCSS demo view"))
    ),
    h2("Functional Programming"),
    ul(DemoStyles.stepsList)(
      li(a(DemoStyles.underlineLinkBlack, href := BindingDemoState().url)("Binding demo")),
      li(a(DemoStyles.underlineLinkBlack, href := DemoStylesState.url)("ScalaCSS demo view"))
    ),
    h2("Aspect Oriented Programming"),
    h3("Resources"),
    ul(
      li(
        a(DemoStyles.underlineLinkBlack, href := "http://udash.io/", target := "_blank")("Visit Udash Homepage.")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "http://guide.udash.io/", target := "_blank")("Read more in Udash Guide.")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "https://www.scala-js.org/", target := "_blank")("Read more about Scala.js.")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "https://japgolly.github.io/scalacss/book/", target := "_blank")("Read more about ScalaCSS")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "http://www.lihaoyi.com/scalatags/", target := "_blank")("Read more about ScalaTags")
      )
    )
  )

  override def getTemplate: Modifier = content

  override def renderChild(view: View): Unit = {}
}