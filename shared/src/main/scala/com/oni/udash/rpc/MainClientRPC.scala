package com.oni.udash.rpc

import com.avsystem.commons.rpc.RPC

/**
 * interface from server to client
 */
@RPC
trait MainClientRPC {
  def push2(number: Int): Unit
  def updateList(quals: List[String]): Unit
}
       