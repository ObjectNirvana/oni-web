package com.oni.udash.styles

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration
import scala.language.postfixOps

import com.oni.udash.styles.utils.MediaQueries
import com.oni.udash.styles.utils.StyleUtils

import scalacss.Defaults.StyleSheet
import scalacss.Defaults.cssComposition
import scalacss.Defaults.cssRegister
import scalacss.internal.Compose

object OniStyles extends StyleSheet.Inline {
  import dsl._

  val linkHoverAnimation = keyframes(
    (0 %%) -> keyframe(color.black),
    (50 %%) -> keyframe(color.red),
    (100 %%) -> keyframe(color.black))

  val navItem = style(
    position.relative,
    display.inlineBlock,
    verticalAlign.middle,
    paddingLeft(1.8 rem),
    paddingRight(1.8 rem),

    &.firstChild(
      paddingLeft(0 px)),

    &.lastChild(
      paddingRight(0 px)),

    &.before.not(_.firstChild)(
      StyleUtils.absoluteMiddle,
      content := "\"|\"",
      left(`0`),

      &.hover(
        textDecoration := "none")))

  val blockOfText = style(
    display.block,
    color.yellow,
    fontSize(9.0 rem),
    width(500 px),
    height(100 px),
    borderColor.red,
    borderWidth(2 px),
    borderStyle.solid,
    &.hover(
      color.white,
      cursor.pointer,
      textDecoration := "none",

      &.after(
        transformOrigin := "0 50%",
        transform := "scaleX(1)")))

  val underlineLink = style(
    position.relative,
    display.block,
    color.yellow,
    fontSize(20.0 rem),
    width(300 px),
    height(100 px),

    &.after(
      StyleUtils.transition(transform, new FiniteDuration(250, TimeUnit.MILLISECONDS)),
      position.absolute,
      top(100 %%),
      left(`0`),
      content := "\" \"",
      width(100 %%),
      borderBottomColor.white,
      borderBottomWidth(1 px),
      borderBottomStyle.solid,
      transform := "scaleX(0)",
      transformOrigin := "100% 50%"),

    &.hover(
      color.white,
      cursor.pointer,
      textDecoration := "none",

      &.after(
        transformOrigin := "0 50%",
        transform := "scaleX(1)")))

  val underlineLinkBlack = style(
    underlineLink,
    display.inlineBlock,
    color.black,

    &.after(
      borderBottomColor.black),

    &.hover(
      color.black))(Compose.trust)

  private val liBulletStyle = style(
    position.absolute,
    left(`0`),
    top(`0`))

  private val liStyle = style(
    position.relative,
    paddingLeft(2 rem),
    margin(.5 rem, `0`, .5 rem, 4.5 rem),

    MediaQueries.phone(
      style(
        marginLeft(1.5 rem))))

  val stepsList = style(
    counterReset := "steps",
    unsafeChild("li")(
      liStyle,

      &.before(
        liBulletStyle,
        counterIncrement := "steps",
        content := "counters(steps, '.')\".\"")))
}