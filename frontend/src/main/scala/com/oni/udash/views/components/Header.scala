package com.oni.udash.views.components
import com.oni.udash.IndexState
import com.oni.udash.config.ExternalUrls
import com.oni.udash.styles.GlobalStyles
import com.oni.udash.styles.partials.HeaderStyles
import org.scalajs.dom.raw.Element

import scalatags.JsDom.all._
import scalacss.ScalatagsCss._
import com.oni.udash.Context._
import scalacss.Defaults._
import scalatags.JsDom.TypedTag

object Header {
  
  val oni_tt = "Created by Object Nirvana, Inc."

  private lazy val template = header(HeaderStyles.header)(
    div(GlobalStyles.body, GlobalStyles.clearfix)(
      div(HeaderStyles.headerLeft, HeaderStyles.headerText)(
        a(HeaderStyles.headerLogo, href := IndexState.url)(
          "Object Nirvana" // Image("udash_logo_m.png", "Udash Framework", GlobalStyles.block)
          )),
      div(HeaderStyles.headerRight)(
        ul(HeaderStyles.headerSocial)(
          li(HeaderStyles.headerSocialItem)(
            a(href := ExternalUrls.udashGithub, HeaderStyles.headerSocialLink, target := "_blank")(
              Image("icon_github.png", "Github"))),
          li(HeaderStyles.headerSocialItem)(
            a(href := ExternalUrls.stackoverflow, HeaderStyles.headerSocialLink, target := "_blank")(
              Image("icon_stackoverflow.png", "StackOverflow"),
              oniTooltip)),
          li(HeaderStyles.headerSocialItem)(
            a(href := ExternalUrls.oniservices, HeaderStyles.headerSocialLinkYellow, target := "_blank")(
              Image("icon_avsystem.png", oni_tt),
              oniTooltip)))))).render

  def oniTooltip: TypedTag[Element] =
    div(HeaderStyles.tooltip)(
      div(HeaderStyles.tooltipTop),
      div(HeaderStyles.tooltipText)(
        div(HeaderStyles.tooltipTextInner)(
          oni_tt)))

  def getTemplate: Element = template
}