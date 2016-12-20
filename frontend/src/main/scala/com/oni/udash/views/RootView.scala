package com.oni.udash.views

import org.scalajs.dom.Element

import com.oni.udash.styles.GlobalStyles
import com.oni.udash.views.components.Footer
import com.oni.udash.views.components.Header

import com.oni.udash.RootState
import io.udash.DefaultViewPresenterFactory
import io.udash.View
import scalacss.ScalatagsCss.styleaToJsDomTag
import scalatags.JsDom.all.div
import scalatags.JsDom.tags2.main

object RootViewPresenter extends DefaultViewPresenterFactory[RootState.type](() => new RootView)

class RootView extends View {
  import scalatags.JsDom.all._

  private val child: Element = div().render

  private val content = div(
    Header.getTemplate,
    main(GlobalStyles.main)(
      div(GlobalStyles.body)(
        //h1("Programming Paradigms"),
        child)),
    Footer.getTemplate)

  override def getTemplate: Modifier = content

  override def renderChild(view: View): Unit = {
    import io.udash.wrappers.jquery._
    jQ(child).children().remove()
    view.getTemplate.applyTo(child)
  }
}