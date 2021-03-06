package com.oni.udash.jetty

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.gzip.GzipHandler
import org.eclipse.jetty.server.session.SessionHandler
import org.eclipse.jetty.servlet.{ DefaultServlet, ServletContextHandler, ServletHolder }
import akka.actor.ActorSystem
import akka.actor.Props
import com.oni.udash.rpc.QualitiesActor
import akka.actor.ActorRef

class ApplicationServer(val port: Int, resourceBase: String) {
  private val server = new Server(port)
  private val contextHandler = new ServletContextHandler

  contextHandler.setSessionHandler(new SessionHandler)
  contextHandler.setGzipHandler(new GzipHandler)
  server.setHandler(contextHandler)

  def start() = server.start()

  def stop() = server.stop()

  private val appHolder = {
    val appHolder = new ServletHolder(new DefaultServlet)
    appHolder.setAsyncSupported(true)
    appHolder.setInitParameter("resourceBase", resourceBase)
    appHolder
  }
  contextHandler.addServlet(appHolder, "/*")

  private val atmosphereHolder = {
    import io.udash.rpc._
    import com.oni.udash.rpc._
    import scala.concurrent.ExecutionContext.Implicits.global

    val config = new DefaultAtmosphereServiceConfig[MainServerRPC]({
      (clientId) =>
        println("new expose")
        new DefaultExposesServerRPC[MainServerRPC](
          new ExposedRpcInterfaces(qualitiesService)(clientId))
    })
    val framework = new DefaultAtmosphereFramework(config)

    //Disabling all files scan during service auto-configuration,
    //as it's quite time-consuming - a few seconds long.
    //
    //If it's really required, enable it, but at the cost of start-up overhead or some tuning has to be made.
    //For that purpose, check what is going on in:
    //- DefaultAnnotationProcessor
    //- org.atmosphere.cpr.AtmosphereFramework.autoConfigureService
    framework.allowAllClassesScan(false)

    framework.init()

    val atmosphereHolder = new ServletHolder(new RpcServlet(framework))
    atmosphereHolder.setAsyncSupported(true)
    atmosphereHolder
  }
  contextHandler.addServlet(atmosphereHolder, "/atm/*")

  //  val cfg = ConfigFactory.parseString("akka.cluster.roles = [" + strRoles.mkString(", ") + "]")
  //      .withFallback(ConfigFactory.load.getConfig("systems.workercluster"))

  val system = ActorSystem("backend") // cfg.getString("system-name"), cfg)
  val qualitiesService: ActorRef = system.actorOf(Props[QualitiesActor])

}
