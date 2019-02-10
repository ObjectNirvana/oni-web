import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys.scalaVersion
import sbt._

object Dependencies {
  val udashVersion2 = "0.5.0"
  val scalatagsVersion = "0.6.5"
  val udashJQueryVersion2 = "1.0.1"
  val jettyVersion2 = "9.3.11.v20160721"
  val jqueryVersion = "0.9.1"
  // val jqueryVersion = "1.0.1" // "0.9.0"
  val akkaVersion = "2.4.17"
  //val scalatestVersion = "3.0.4"
  val scalazVersion = "7.2.8"
  val scalacssVersion = "0.5.3"

    // Versions
  val scalaVersions           = Seq("2.11.12", "2.12.4")
  val scalaCoreVersion        = "2.12.4"
  val mongodbDriverVersion    = "3.6.3"

  val scalaTestVersion        = "3.0.4"
  val scalaMockVersion        = "3.4.1"
  val logbackVersion          = "1.1.3"
  val reflectionsVersion      = "0.9.10"
  val javaxServeletApiVersion = "2.5"
  val nettyVersion            = "4.1.17.Final"

  val rxScalaVersion          = "0.26.4"
  val rxStreamsVersion        = "1.0.0"

  // Libraries
  val mongodbDriver = "org.mongodb" % "mongodb-driver-async" % mongodbDriverVersion
  val scalaReflect  =  scalaVersion("org.scala-lang" % "scala-reflect" % _)

  // Test
  val scalaTest         = "org.scalatest" %% "scalatest" % scalaTestVersion % "it,test"
  val scalaMock         = "org.scalamock" %% "scalamock-scalatest-support" % scalaMockVersion % "test"
  val logback           = "ch.qos.logback" % "logback-classic" % logbackVersion % "it,test"
  val reflections       = "org.reflections" % "reflections" % reflectionsVersion % "test"
  val javaxServeletApi  = "javax.servlet" % "servlet-api" % javaxServeletApiVersion % "test"
  val nettyTest         = "io.netty" % "netty-all" % nettyVersion % "test"
  val nettyIt            = "io.netty" % "netty-all" % nettyVersion % "it"

  // Examples
  val rxScala           = "io.reactivex" %% "rxscala" % rxScalaVersion
  val rxStreams         = "org.reactivestreams" % "reactive-streams" % rxStreamsVersion


  val crossDeps2 = Def.setting(Seq[ModuleID](
    "io.udash" %%% "udash-core-shared" % udashVersion2,
    "io.udash" %%% "udash-rpc-shared" % udashVersion2
  ))

  val frontendDeps2 = Def.setting(Seq[ModuleID](
    "org.scalatest" %%% "scalatest" % scalaTestVersion % "test",
    "com.lihaoyi" %%% "utest" % "0.4.7" % "test",
    "com.lihaoyi" %% "scalatags" % scalatagsVersion,
    "be.doeraene" %%% "scalajs-jquery" % jqueryVersion,
    "io.udash" %%% "udash-core-frontend" % udashVersion2,
    "io.udash" %%% "udash-jquery" % udashJQueryVersion2,
    "io.udash" %%% "udash-rpc-frontend" % udashVersion2,
    //"com.github.japgolly.scalajs-react" %%% "core" % "0.11.3",
    "com.github.japgolly.scalacss" %%% "core" % scalacssVersion,
    "com.github.japgolly.scalacss" %%% "ext-scalatags" % scalacssVersion
  ))

  val frontendJSDeps2 = Def.setting(Seq[org.scalajs.sbtplugin.JSModuleID](
  ))

/*
  val backendDeps2 = Seq[ModuleID](
    // mongodbDriver,
//    "org.mongodb" % "mongodb-driver-async" % mongodbDriverVersion,
    // scalaReflect,
    "org.scalaz" %% "scalaz-core" % scalazVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "org.eclipse.jetty" % "jetty-server" % jettyVersion2,
    "org.eclipse.jetty" % "jetty-servlet" % jettyVersion2,
    "io.reactivex" %% "rxscala" % rxScalaVersion,
    //"org.mongodb.scala" %% "mongo-scala-driver" % "2.2.1",
    "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1",
    //"org.mongodb" %% "casbah" % "3.1.1",
    "com.github.salat" %% "salat" % "1.11.3-SNAPSHOT", // "1.11.1",
    "io.udash" %% "udash-rpc-backend" % udashVersion2,
    "org.eclipse.jetty.websocket" % "websocket-server" % jettyVersion,
    scalaMock
  )
//}
*/

  val jettyVersion = "9.4.8.v20171121"
  val udashVersion = "0.6.0"
  val udashJQueryVersion = "1.1.0"
  val bootstrapVersion = "3.3.7-1"
  val scalatestVersion = "3.0.4"
  // Dependencies for both frontend and backend
  // Those have to be cross-compilable
  val crossDeps = Def.setting(Seq(
    "io.udash" %%% "udash-core-shared" % udashVersion,
    "io.udash" %%% "udash-rpc-shared" % udashVersion
  ))
  // Dependencies compiled to JavaScript code
  val frontendDeps = Def.setting(Seq(
    "io.udash" %%% "udash-core-frontend" % udashVersion,
    "io.udash" %%% "udash-rpc-frontend" % udashVersion,
    // type-safe wrapper for jQuery
    "io.udash" %%% "udash-jquery" % udashJQueryVersion,
    "org.scalatest" %%% "scalatest" % scalaTestVersion % "test",
    "com.lihaoyi" %%% "utest" % "0.4.7" % "test",
    "com.lihaoyi" %% "scalatags" % scalatagsVersion,
    "be.doeraene" %%% "scalajs-jquery" % jqueryVersion,
    //"io.udash" %%% "udash-core-frontend" % udashVersion,
    //"io.udash" %%% "udash-jquery" % udashJQueryVersion,
    //"io.udash" %%% "udash-rpc-frontend" % udashVersion,
    //"com.github.japgolly.scalajs-react" %%% "core" % "0.11.3",
    "com.github.japgolly.scalacss" %%% "core" % scalacssVersion,
    "com.github.japgolly.scalacss" %%% "ext-scalatags" % scalacssVersion
  ))
  // JavaScript libraries dependencies
  // Those will be added into frontend-deps.js
  val frontendJSDeps = Def.setting(Seq(
    // it's optional of course
    // "jquery.js" is provided by "udash-jquery" dependency
    "org.webjars" % "bootstrap" % bootstrapVersion /
      "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js"
  ))
  // Dependencies for JVM part of code
  val backendDeps = Def.setting(Seq(
    // "io.udash" %% "udash-rpc-backend" % udashVersion,
    "org.eclipse.jetty" % "jetty-server" % jettyVersion,
    "org.eclipse.jetty.websocket" % "websocket-server" % jettyVersion,
    "org.scalaz" %% "scalaz-core" % scalazVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "org.eclipse.jetty" % "jetty-server" % jettyVersion,
    "org.eclipse.jetty" % "jetty-servlet" % jettyVersion,
    "io.reactivex" %% "rxscala" % rxScalaVersion,
    //"org.mongodb.scala" %% "mongo-scala-driver" % "2.2.1",
    "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1",
    //"org.mongodb" %% "casbah" % "3.1.1",
    "com.github.salat" %% "salat" % "1.11.3-SNAPSHOT", // "1.11.1",
    "io.udash" %% "udash-rpc-backend" % udashVersion,
    scalaMock
  ))
  // Test dependencies
  val crossTestDeps = Def.setting(Seq(
    "org.scalatest" %%% "scalatest" % scalatestVersion
  ).map(_ % Test))
}
