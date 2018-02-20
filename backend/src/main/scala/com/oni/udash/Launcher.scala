package com.oni.udash

import com.oni.udash.jetty.ApplicationServer

object Launcher {
  def main(args: Array[String]): Unit = {
    val port = 12013
    val server = new ApplicationServer(port, "backend/target/UdashStatic/WebContent")
    server.start()
  }
}

       