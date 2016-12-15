package com.oni.udash.views

import io.udash._
import com.oni.udash.IndexState
import org.scalajs.dom.Element

object ErrorViewPresenter extends DefaultViewPresenterFactory[IndexState.type](() => new ErrorView)

class ErrorView extends View {
  import scalatags.JsDom.all._

  private val content = h3(
    "URL not found!"
  )

  override def getTemplate: Modifier = content

  override def renderChild(view: View): Unit = {}
}