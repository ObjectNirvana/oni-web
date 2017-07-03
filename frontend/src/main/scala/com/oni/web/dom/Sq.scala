package com.oni.web.dom

import io.udash.properties.{ModelPart, PropertyCreator, seq}

/**
 * 
 */
case class SqW(id: String,
    desc: String,
    details: Option[String] = None)

object SqW {
  def apply(sq: Sq): SqW = SqW(sq.id, sq.desc, sq.details)

  implicit val pc: PropertyCreator[Seq[SqW]] = PropertyCreator.propertyCreator[Seq[SqW]]
}
