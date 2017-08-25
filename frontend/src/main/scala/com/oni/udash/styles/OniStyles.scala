package com.oni.udash.styles

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration
import scala.language.postfixOps

import com.oni.udash.styles.utils.MediaQueries
import com.oni.udash.styles.utils.StyleUtils

import scalacss.DevDefaults.StyleSheet
import scalacss.DevDefaults.cssComposition
import scalacss.DevDefaults.cssRegister
import scalacss.internal.Compose

object OniStyles2 extends StyleSheet.Standalone {
  import dsl._

  "#box figure" - (
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

//    "#box.panels-backface-invisible figure" - (
//      backfaceVisibility.hidden
//    )

    val xw = 300 px
    val yw = 300 px
    val zw = 300 px

    "#box" - (
      width( 100%%),
      height(100%%),
      position.absolute,
      transformStyle.preserve3D,
      //transformStyle("preserve-3d"),
      transition := "transform 1s"
    )

    "#box .OniStyles-frontSide, #box .OniStyles-backSide" - (
      width(xw),
      height(yw)
    )

    "#box .OniStyles-rightSide, #box .OniStyles-leftSide" - (
      width(zw),
      height(yw),
      left(100 px)
    )

    "#box .OniStyles-topSide, #box .OniStyles-bottomSide" - (
      width(xw),
      height(zw),
      top(50 px),
      lineHeight(20 px)
    )

    "#box.show-frontSide" - (
      transform:= "translateZ(  -50px )"
    )
    "#box.show-backSide" - (
      transform:= "translateZ( -50px ) rotateX( -180deg )"
    )
    "#box.show-rightSide" - (
      transform:= "translateZ( -150px ) rotateY(  -90deg )"
    )
    "#box.show-leftSide" - (
      transform:= "translateZ( -150px ) rotateY(   90deg )"
    )
    "#box.show-topSide" - (
      transform:= "translateZ( -100px ) rotateX(  -90deg )"
    )
    "#box.show-bottomSide" - (
      transform:= "translateZ( -100px ) rotateX(   90deg )"
    )
}

object OniStyles extends StyleSheet.Inline {
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

  val cubeBox = style(
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

    val zxlate = 70

  val frontSide = style(
    background := frontColor, //  := hsla( 0, 100 %%, 50 %%, 0.7 ),
    transform := s"translateZ( ${zxlate}px )")

  val backSide = style(
    background := backColor, // hsla( 160, 100 %%, 50 %%, 0.7 ),
    transform := s"rotateX( -180deg ) translateZ( ${zxlate}px )")

  val rightSide = style(
    background := rightColor, // hsla( 120, 100 %%, 50%%, 0.7 ),
    transform := s"rotateY( 90deg ) translateZ( ${zxlate}px )")

  val leftSide = style(
    background := leftColor, // hsla( 180, 100%%, 50%%, 0.7 ),
    transform := s"rotateY(  -90deg ) translateZ( ${zxlate}px )")

  val topSide = style(
    background := topColor, // hsla( 240, 100%%, 50%%, 0.7 ),
    transform := s"rotateX(   90deg ) translateZ( ${zxlate}px )")

  val bottomSide = style(
    background := bottomColor, // hsla( 300, 100%%, 50%%, 0.7 ),
    transform := s"rotateX(  -90deg ) translateZ( ${zxlate}px )")

}
