package com.oni.web.sq

import org.json4s.DefaultFormats
import org.json4s.DefaultFormats._
import org.json4s._
import org.json4s.ParserUtil._
import org.json4s.native.JsonMethods._
import org.json4s.Serialization
import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{ read, write, writePretty }

//import org.junit.{Test, Assert, Before}
//import org.junit.Assert._
//import net.liftweb.json._

case class ProJson(eventProperties: Map[String, Any])
case class ProJson2(eventProperties: Map[String, Any])

object JsonTest {
  //object TestFields {

  val aa = (s: String) => s + "a"
  val ab = (s: String) => s + "b"
  def aba = aa compose ab

  def main(args: Array[String]) = {

    implicit val formats = Serialization.formats(
      ShortTypeHints(
        List(
          classOf[ProJson],
          classOf[ProJson2]
          //                classOf[Dollar],
          //                classOf[Address],
          //                classOf[GpsCoords]
          )))

    //implicit val formats = Serialization.formats(NoTypeHints)
    val json = parse("""
                  { "key":"01234",
                    "eventProperties":{
                      "unknownProperty1":"value",
                      "unknownProperty2":"value",
                      "unknownProperty3":"value"
                    }
                  }
                  """)
    println("parsed json")
    println("json: " + json)

    val pj = ProJson(Map())
    val pj_json = write(pj)

    val t1 = read[Any](pj_json)

    t1 match {
      case ProJson(e) =>
      case ProJson2(e) =>
      case x =>
        println("oops: " + x)
    }

    // implicit val formats = DefaultFormats
    //val eventPropertiesMap = json.extract[ProJson]
  }
}

// Can we make eventPropertiesMap as a Json Object ?

//import org.junit.{Test, Assert, Before}
//import org.junit.Assert._
//import net.liftweb.json._
//
//case class Element(title:String, params:JObject)
//@Test
//class JsonParserTest{
// implicit val formats = Serialization.formats(NoTypeHints)
//
// @Test
//  def testLiftMapToAny{
//    val result = parse("""{"title":"foobar","params":{"one":1,"two":2, "other": "give"}}""").extract[Element]
//
//    assertEquals("foobar", result.title)
//    assertEquals(Map("one" -> 1, "two" -> 2, "other" -> "give"), result.params.values)
//  }
//}
