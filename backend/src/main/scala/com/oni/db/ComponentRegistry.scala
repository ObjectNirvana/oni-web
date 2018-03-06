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

object ComponentRegistry
    extends SqDaoComponentImpl
    with SqServiceComponentImpl {

  override val sqDao: SqDao = new SqDaoImpl

  val SqService = new SqServiceImpl
}
