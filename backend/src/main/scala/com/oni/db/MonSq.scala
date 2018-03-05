package com.oni.db

//import com.novus.salat._
//import com.novus.salat.dao._
//import com.novus.salat.global._
import salat._
import salat.dao._
import salat.global._

import com.mongodb.casbah.Imports._
import salat.dao.SalatDAO
import com.oni.web.dom.Sq

case class MonSq(_id:ObjectId = new ObjectId,
    desc: String,
    det: String = "",
    details: Option[String] = None) {
  def toDom = Sq(_id.toString, desc, details)
  def toDomSmall = Sq(_id.toString, desc)
}

object MonSq {
  def apply(sq: Sq): MonSq = MonSq(new ObjectId(sq.id), sq.desc, sq.details.getOrElse(""), sq.details)
  
}
