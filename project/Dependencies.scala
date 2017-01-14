import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object Dependencies {
  val udashVersion = "0.4.0"
  val scalatagsVersion = "0.6.2"
  val udashJQueryVersion = "1.0.0"
  val logbackVersion = "1.1.3"
  val jettyVersion = "9.3.11.v20160721"
  val jqueryVersion = "0.9.0"
  val rxScalaVersion = "0.26.4"
  val akkaVersion = "2.4.3"

  val crossDeps = Def.setting(Seq[ModuleID](
    "io.udash" %%% "udash-core-shared" % udashVersion,
    "io.udash" %%% "udash-rpc-shared" % udashVersion
  ))

  val frontendDeps = Def.setting(Seq[ModuleID](
    "be.doeraene" %%% "scalajs-jquery" % jqueryVersion,
    "io.udash" %%% "udash-core-frontend" % udashVersion,
    "com.lihaoyi" %% "scalatags" % scalatagsVersion,
    "io.udash" %%% "udash-jquery" % udashJQueryVersion,
    "io.udash" %%% "udash-rpc-frontend" % udashVersion,
    //"com.github.japgolly.scalajs-react" %%% "core" % "0.11.3",
    "com.github.japgolly.scalacss" %%% "core" % "0.5.0",
    "com.github.japgolly.scalacss" %%% "ext-scalatags" % "0.5.0"
  ))

  val frontendJSDeps = Def.setting(Seq[org.scalajs.sbtplugin.JSModuleID](
  ))

  val backendDeps = Def.setting(Seq[ModuleID](
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "org.eclipse.jetty" % "jetty-server" % jettyVersion,
    "org.eclipse.jetty" % "jetty-servlet" % jettyVersion,
    "io.reactivex" %% "rxscala" % rxScalaVersion,
    //"org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1",
    //"org.mongodb" %% "casbah" % "3.1.1",
    "com.github.salat" %% "salat" % "1.10.0",
    "io.udash" %% "udash-rpc-backend" % udashVersion,
    "org.eclipse.jetty.websocket" % "websocket-server" % jettyVersion
  ))
}