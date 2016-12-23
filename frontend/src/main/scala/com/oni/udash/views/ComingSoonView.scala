package com.oni.udash.views

import io.udash._
import com.oni.udash._
import org.scalajs.dom.Element
import com.oni.udash.styles.{DemoStyles, GlobalStyles}
import scalacss.ScalatagsCss._

object ComingSoonViewPresenter extends DefaultViewPresenterFactory[ComingSoonState.type](() => new ComingSoonView)

class ComingSoonView extends View {
  import com.oni.udash.Context._
  import scalatags.JsDom.all._

  private val content = div(
    h1("Coming Soon")
  )

  override def getTemplate: Modifier = content

  override def renderChild(view: View): Unit = {}
}