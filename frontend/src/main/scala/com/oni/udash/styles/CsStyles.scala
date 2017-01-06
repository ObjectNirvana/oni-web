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

object CsStyles2 extends StyleSheet.Standalone {
  import dsl._

  "#box_cs figure" - (
      display.block,
      position.absolute,
      padding( 2 rem ),
      border( 1 px, solid, white), // black;
      lineHeight( 22 px),
      fontSize(17 px),
      textAlign.left,
      //fontWeight.bold,
      color(white)
    )

//    "#box_cs.panels-backface-invisible figure" - (
//      backfaceVisibility.hidden
//    )

    val xw = 300 px
    val yw = 300 px
    val zw = 300 px

    "#box_cs" - (
      width( 100%%),
      height(100%%),
      position.absolute,
      transformStyle.preserve3D,
      //transformStyle("preserve-3d"),
      transition := "transform 1s"
    )

    "#box_cs .CsStyles-frontSide, #box_cs .CsStyles-backSide" - (
      width(xw),
      height(yw)
    )

    "#box_cs .CsStyles-rightSide, #box_cs .CsStyles-leftSide" - (
      width(zw),
      height(yw)
      //left(100 px)
    )

    "#box_cs .CsStyles-topSide, #box_cs .CsStyles-bottomSide" - (
      width(xw),
      height(zw),
      //top(50 px),
      lineHeight(20 px)
    )

    "#box_cs.show-frontSide" - (
      transform:= "translateZ(  -50px )"
    )
    "#box_cs.show-backSide" - (
      transform:= "translateZ( -50px ) rotateY( 10deg )"
    )
    "#box_cs.show-rightSide" - (
      transform:= "translateZ( -50px ) rotateY( 20deg )"
    )
    "#box_cs.show-leftSide" - (
      transform:= "translateZ( -50px ) rotateY( 30deg )"
    )
    "#box_cs.show-topSide" - (
      transform:= "translateZ( -50px ) rotateY( 40deg )"
    )
    "#box_cs.show-bottomSide" - (
      transform:= "translateZ( -50px ) rotateY( 50deg )"
    )
}

object CsStyles extends StyleSheet.Inline {
  import dsl._

  val linkHoverAnimation = keyframes(
    (0 %%) -> keyframe(color.black),
    (50 %%) -> keyframe(color.red),
    (100 %%) -> keyframe(color.black))

  val cubeContainer = style(
      paddingTop( 20 px ),
      width( 300 px ),
      height( 300 px ),
      position.relative,
      margin( 0 px, auto, 20 px),
      //border( 1 px, solid, yellow ), // , color.yellow),
      //-webkit-perspective: 1200px;
      //   -moz-perspective: 1200px;
      //     -o-perspective: 1200px;
      perspective(1200 px)
    )

  val cubebox_cs = style(
      width( 100%%),
      height(100%%),
      position.absolute,
      transformStyle.preserve3D,
      //transformStyle("preserve-3d"),
      transition := "transform 1s"
    )

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
    float.left,
    color.yellow,
    fontSize(7.0 rem),
    width(300 px),
    height(80 px),
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
    top(`0`)
  )

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

  val intensity = 80
  val frontColor  = rgb( 0, 0, intensity )
  val backColor   = rgb( 0, intensity, 0 )
  val leftColor   = rgb( intensity, 0, 0 )
  val rightColor  = rgb( 0, intensity, intensity )
  val topColor    = rgb( intensity, 0, intensity )
  val bottomColor = rgb( intensity, intensity, 0 )

  val zxlate = 10

  val frontSide = style(
    background := frontColor, //  := hsla( 0, 100 %%, 50 %%, 0.7 ),
    left(20 px),
    transform := s"translateZ( ${zxlate}px )")

  val backSide = style(
    background := backColor, // hsla( 160, 100 %%, 50 %%, 0.7 ),
    left(40 px),
    transform := s"rotateY( 20deg ) translateZ( ${zxlate}px )")

  val rightSide = style(
    background := rightColor, // hsla( 120, 100 %%, 50%%, 0.7 ),
    left(60 px),
    transform := s"rotateY( 40deg ) translateZ( ${zxlate}px )")

  val leftSide = style(
    background := leftColor, // hsla( 180, 100%%, 50%%, 0.7 ),
    left(80 px),
    transform := s"rotateY(  60deg ) translateZ( ${zxlate}px )")

  val topSide = style(
    background := topColor, // hsla( 240, 100%%, 50%%, 0.7 ),
    left(100 px),
    transform := s"rotateY(  80deg ) translateZ( ${zxlate}px )")

  val bottomSide = style(
    background := bottomColor, // hsla( 300, 100%%, 50%%, 0.7 ),
    left(120 px),
    transform := s"rotateY( 100deg ) translateZ( ${zxlate}px )")

}
