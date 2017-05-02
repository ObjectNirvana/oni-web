package com.oni.udash.views

import io.udash._
import com.oni.udash._
import org.scalajs.dom.Element
import scalacss.ScalatagsCss._
import com.oni.udash.styles.CsStyles
import com.oni.udash.ComingSoonState
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Future
import com.oni.web.dom.Sq

case class SqDetailsViewPresenter()
  extends DefaultViewPresenterFactory[SqDetailsState.type](() => {
    import com.oni.udash.Context._

    val serverQualities = SeqProperty[Sq](List(Sq("1", "empty")))

    val getList = serverRpc.getList()
    getList.onComplete {
      case Success(resp) =>
        serverQualities.set(resp)
      case Failure(_) =>
        serverQualities.set(List(Sq("e", "Error")))
    }

    getList.value
    // Future.sequence(getList)

    val model = Property[String]("/")
    val sqDetails = Property[String]("")
    new SqDetailsView(model, sqDetails, serverQualities)
  })

class SqDetailsView(model: Property[String],
    sqDetails: Property[String],
    serverQualities: SeqProperty[Sq]) extends View {
  import com.oni.udash.Context._
  import scalatags.JsDom.all._

  import OniText._
  import CsStyles._
  import scalatags.JsDom.all._

  def saveQualityDetails() = {
    println("save qd")
    import org.scalajs.jquery.jQuery
    val details = jQuery("#sqd").text
    // sq = sq.map(_.copy(details = Some(sqDetails.get)))
    sq = sq.map(_.copy(details = Some(details)))
    // serverRpc.saveDetails(sq.get.id, sqDetails.get)
    serverRpc.saveDetails(sq.get.id, details)
  }

  def saveQuality() = {
    println("save q")
    serverRpc.save(model.get)
    model.set("")
//    println("save q 2")
//    serverRpc.hello(model.get).onComplete {
//      case Success(a) =>
//        println("a here")
//      case Failure(f) =>
//        println("a f here")
//    }
    println("get list")
    serverRpc.getList().onComplete {
      case Success(resp) =>
        serverQualities.set(resp)
      case Failure(_) =>
        serverQualities.set(List(Sq("e1", "Error")))
    }
    println("saved")
  }

  var cbnum = 1

  var sq: Option[Sq] = None

  def getSqDetails(id: String)(): Unit = {
    println("get details")
    serverRpc.getSqDetails(id).onComplete {
      case Success(resp) =>
        sq = Some(resp)
        model.set(resp.desc)
        import org.scalajs.jquery.jQuery
        jQuery("#sqd").text(resp.details.getOrElse(""))
        sqDetails.set(resp.details.getOrElse(""))
      case Failure(_) =>
        sq = None
        sqDetails.set("Error")
    }
    println("got details")
  }

  private val content = div(
    h2("Add a new software quality to our list!"),
    div("""We are gathering the list of software qualities, processes, features, whatever you think
           makes writing software better or more cool.
        """),
    div(CsStyles.sqContainer)(
      div(CsStyles.leftSq)(
        h3("Tell us what makes writing software better:"),
        TextInput.debounced(model, placeholder := "Enter your favorite software quality..."),
        p("Your quality: ", bind(model)),
        button(onclick := saveQuality _)("Submit Quality")
      ),

      div(CsStyles.rightSq)(
        h3("Details for: ", bind(model)),
        div(id:="sqd",
            title:="click to edit",
            attr("contenteditable"):=("true"))( bind(sqDetails) ),
        //p("Comments"),
        //TextInput.debounced(sqDetails, placeholder := "Describe why"),
        button(onclick := saveQualityDetails _)("Save")
      )

    ),

    script("""
      var init = function() {

      };

    """)
  )

  override def getTemplate: Modifier = content

  override def renderChild(view: View): Unit = {}
}