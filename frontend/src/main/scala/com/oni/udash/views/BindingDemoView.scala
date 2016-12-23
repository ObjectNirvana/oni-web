package com.oni.udash.views

import io.udash._
import com.oni.udash.BindingDemoState
import org.scalajs.dom.Element
import com.oni.udash.styles.DemoStyles
import scalacss.ScalatagsCss._
import io.udash._
//import io.udash.web.commons.components.CodeBlock
//import io.udash.web.guide._
//import io.udash.web.guide.styles.partials.GuideStyles
//import io.udash.web.guide.views.ext.demo.BootstrapDemos
//import io.udash.web.guide.views.{References, Versions}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLStyleElement

import scalatags.JsDom
import scalatags.JsDom.TypedTag
//import com.oni.udash.styles.constants.StyleConstants
//import com.oni.udash.styles.utils.{MediaQueries, StyleUtils}

import scala.concurrent.duration.FiniteDuration
import scala.language.postfixOps
//import scalacss.Defaults._
import com.oni.udash.styles.fonts.UdashFonts
import com.oni.udash.styles.fonts.FontWeight
import com.oni.udash.styles.OniStyles

case class BindingDemoViewPresenter(urlArg: String) extends DefaultViewPresenterFactory[BindingDemoState](() => {
  import com.oni.udash.Context._

  val model = Property[String](urlArg)
  new BindingDemoView(model)
})

class BindingDemoView(model: Property[String]) extends View {
  //import scalacss.ScalatagsCss._
  //import scalacss.Defaults._
  //import dsl._

  private val content = {
    import scalatags.JsDom.all._
    div(
    h2(
      "You can find this demo source code in: ",
      i("com.oni.udash.views.BindingDemoView")
    ),
    h3("Example"),
    TextInput.debounced(model, placeholder := "Type something..."),
    p("You typed: ", bind(model)),
    h3("Read more"),
    // CodeBllock
    //div(OniStyles.obliqueOnHover)("a div", onclick:={ () => mydivf() }),
    div(OniStyles.blockOfText)("a 123 div", onclick := mydivf _),
    p("other text 1"),
    div(OniStyles.blockOfText)("a 456 div", onclick := mydivf _),
    p("other text 2"),
    // p(LocalStyles.obliqueOnHover)("Hover me!"),
    a(DemoStyles.underlineLinkBlack)(href := "http://guide.udash.io/#/frontend/bindings", target := "_blank")("Read more in Udash Guide.")
  )
  }

  def mydivf(): Unit = {
    //"alert('abc')"
    //window.alert("abc")
    import scala.scalajs.js.timers._

    setTimeout(1000) { // note the absence of () =>
      // work
      import scala.scalajs.js
      import js.Dynamic.{ global => g }

      g.alert("Hello from Scala")
    }
  }

//  val svgFrag = {
//    //import bundle.implicits._
//    import scalatags.jsdom._ // SvgTags._
//    import scalatags.jsdom.SvgTags._
//    //import bundle.svgAttrs._
//    svg(height := "800", width := "500")(
//      polyline(
//        points := "20,20 40,25 60,40 80,120 120,140 200,180",
//        fill := "none",
//        stroke := "black",
//        strokeWidth := "3"
//      ),
//      line(
//        x1 := 175, y1 := 100,
//        x2 := 275, y2 := 0,
//        stroke := "rgb(255,0,0)",
//        strokeWidth := 10
//      ),
//      rect(
//        x := 300, y := 10,
//        rx := 20, ry := 20,
//        width := 100,
//        height := 100,
//        fill := "rgb(0,0,255)",
//        strokeWidth := 3,
//        stroke := "rgb(0,0,0)",
//        fillOpacity := "0.1",
//        strokeOpacity := "0.5"
//      ),
//      circle(
//        cx := 30, cy := 250,
//        r := 10,
//        stroke := "black",
//        strokeWidth := 3,
//        fill := "red"
//      ),
//      ellipse(
//        cx := 150, cy := 250,
//        rx := 100, ry := 50,
//        fill := "yellow",
//        stroke := "purple",
//        strokeWidth := 4
//      ),
//      polygon(
//        points := "300,110 350,290 260,310",
//        fill := "line",
//        stroke := "purple",
//        strokeWidth := 10
//      ),
//      path(
//        d := "M100 300 L25 500 L175 500 Z"
//      ),
//      text(
//        x := 350, y := 250,
//        fill := "red",
//        transform := "rotate(30 20, 40)",
//        "I love SVG!"
//      ),
//      text(x := 350, y := 350, fill := "green")(
//        "Several lines",
//        tspan(x := 350, y := 380, "First line."),
//        tspan(x := 350, y := 410, "Second line.")
//      ),
//      defs(
//        linearGradient(
//          id := "grad1",
//          x1 := "0%",
//          y1 := "0%",
//          x2 := "100%",
//          y2 := "0%",
//          stop(
//            offset := "0%",
//            stopColor := "rgb(255,255,0)"
//          ),
//          stop(
//            offset := "100%",
//            stopColor := "rgb(255,0,0)"
//          )
//        )
//      ),
//      ellipse(
//        cx := 100, cy := 590,
//        rx := 85, ry := 55,
//        fill := "url(#grad1)"
//      )
//    )
//  }

//  import scalacss.defaults.Exports.StyleSheet
  import scalacss.Defaults._
//  import scalacss.ScalatagsCss._
  import scalatags.JsDom._
//  import scalatags.JsDom.all._
//  object LocalStyles extends StyleSheet.Inline {
//    import dsl._
//    // import scalacss.Defaults._
//
//    val redItalic = style(
//      fontStyle.italic,
//      color.red
//    )
//
//    val obliqueOnHover = style(
//      fontStyle.normal,
//      // fontSize(10.0 rem),
//      // height(scalatags.JsDom.all.CssNumber(40) px)
//      //width(scalacss.internal.DslBase.DslNum.autoDslNum(300) px),
//
//      &.hover(
//        fontStyle.oblique
//      )
//    )
//  }

  override def getTemplate: Modifier = content

  override def renderChild(view: View): Unit = {}
}