logLevel := Level.Warn
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.3")
//addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.12")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.19")

addSbtPlugin("com.lihaoyi" % "workbench" % "0.2.3")
// addSbtPlugin("com.lihaoyi" % "workbench" % "0.4.0")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")
//addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.2")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")

// addSbtPlugin("com.sqality.scct" % "sbt-scct" % "0.3.1-SNAPSHOT")

addSbtPlugin("com.tapad" % "sbt-docker-compose" % "1.0.27")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.7.0")
