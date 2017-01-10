package com.oni.udash.rpc

class RPCService extends MainClientRPC {
  override def push2(number: Int): Unit = {
    println(s"Push from server: $number")
  }
  override def updateList(quals: List[String]): Unit = {
    //println(s"save from server: $newQuality")
    println(s"update list ave from server")
      import scala.scalajs.js
      import js.Dynamic.{ global => g }

      g.alert("Hello 2 from Scala")
      List("one")
  }
}

       