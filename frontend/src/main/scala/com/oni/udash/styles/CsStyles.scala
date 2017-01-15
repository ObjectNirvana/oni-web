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

  "#sqd" - (
      minHeight(1 rem),
      minWidth(10 rem),
      &.hover(
        backgroundColor(brown),
        border( 1 px, solid, gray)
      )
  )

  "#box_cs figure" - (
      display.block,
      position.absolute,
      //top( -30 px ),
      padding( 2 rem ),
      border( 2 px, solid, white), // black;
      lineHeight( 22 px),
      fontSize(17 px),
      textAlign.left,
      //fontWeight.bold,
      transition := "transform 2s",
      color(white)
    )

  ".CsStyles-middleSq li" - (
    fontSize(15 px),
    &.hover(
      backgroundColor:=!"#aa0000"
    )
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
      transition := "transform 2s"
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

//    "#box_cs.show-frontSide" - (
//      transform:= "translateZ(  -50px )"
//    )
//    "#box_cs.show-backSide" - (
//      transform:= "translateZ( -50px ) rotateY( 10deg )"
//    )
//    "#box_cs.show-rightSide" - (
//      transform:= "translateZ( -50px ) rotateY( 20deg )"
//    )
//    "#box_cs.show-leftSide" - (
//      transform:= "translateZ( -50px ) rotateY( 30deg )"
//    )
//    "#box_cs.show-topSide" - (
//      transform:= "translateZ( -50px ) rotateY( 40deg )"
//    )
//    "#box_cs.show-bottomSide" - (
//      transform:= "translateZ( -50px ) rotateY( 50deg )"
//    )
}

object CsStyles extends StyleSheet.Inline {
  import dsl._

  val linkHoverAnimation = keyframes(
    (0 %%) -> keyframe(color.black),
    (50 %%) -> keyframe(color.red),
    (100 %%) -> keyframe(color.black))

  val far = style(
    transform := "translateZ( -1000px )")

  val near = style(
    top:=! "-160px !important",
    transform := "translateZ( 10px ) !important")

  val leftSq = style(
      float.left,
      //border( 1 px, solid, yellow ),
      width( 300 px ),
      height( 200 px )
  )

  val middleSq = style(
      float.left,
      //border( 1 px, solid, yellow ),
      paddingLeft(10 px),
      width( 300 px ),
      height( 200 px )
  )

  val rightSq = style(
      float.left,
      //border( 1 px, solid, yellow ),
      paddingLeft(10 px),
      width( 300 px ),
      height( 200 px )
  )

  val sqContainer = style(
      width( 100 %% )
      //height( 400 px )
  )

  val cubeContainer = style(
      paddingTop( 120 px ),
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

  val intensity = 80
  val frontColor  = rgb( 0, 0, intensity )
  val backColor   = rgb( 0, intensity, 0 )
  val leftColor   = rgb( intensity, 0, 0 )
  val rightColor  = rgb( 0, intensity, intensity )
  val topColor    = rgb( intensity, 0, intensity )
  val bottomColor = rgb( intensity, intensity, 0 )

  val zxlate = -1300

  val frontSide = style(
    background := frontColor, //  := hsla( 0, 100 %%, 50 %%, 0.7 ),
    left(20 px),
    top(0 px),
    transform := s"translate3d( 30px, 200px, ${zxlate}px ) rotateX(80deg)")

  val backSide = style(
    background := backColor, // hsla( 160, 100 %%, 50 %%, 0.7 ),
    left(40 px),
    top(0 px),
    transform := s"translate3d( 30px, 200px, ${zxlate}px ) rotateX(80deg)")

  val rightSide = style(
    background := rightColor, // hsla( 120, 100 %%, 50%%, 0.7 ),
    left(60 px),
    top(0 px),
    transform := s"translate3d( 30px, 200px, ${zxlate}px ) rotateX(80deg)")

  val leftSide = style(
    background := leftColor, // hsla( 180, 100%%, 50%%, 0.7 ),
    left(80 px),
    top(0 px),
    transform := s"translate3d( 30px, 200px, ${zxlate}px ) rotateX(80deg)")

  val topSide = style(
    background := topColor, // hsla( 240, 100%%, 50%%, 0.7 ),
    left(100 px),
    top(0 px),
    transform := s"translate3d( 30px, 200px, ${zxlate}px ) rotateX(80deg)")

  val bottomSide = style(
    background := bottomColor, // hsla( 300, 100%%, 50%%, 0.7 ),
    left(120 px),
    top(0 px),
    transform := s"translate3d( 30px, 200px, ${zxlate}px ) rotateX(80deg)")

}
