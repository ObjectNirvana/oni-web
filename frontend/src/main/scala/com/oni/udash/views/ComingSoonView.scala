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

case class ComingSoonViewPresenter()
  extends DefaultViewPresenterFactory[ComingSoonState.type](() => {
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
    new ComingSoonView(model, sqDetails, serverQualities)
  })

class ComingSoonView(model: Property[String],
    sqDetails: Property[String],
    serverQualities: SeqProperty[Sq]) extends View {
  import com.oni.udash.Context._
  import scalatags.JsDom.all._

  import OniText._
  import CsStyles._
  import scalatags.JsDom.all._

  val buttons = List(
      button( id:="cb1", cls:="show-frontSide")("Show 1"),
      button( id:="cb2", cls:="show-backSide")("Show 2"),
      button( id:="cb3", cls:="show-rightSide")("Show 3"),
      button( id:="cb4", cls:="show-leftSide")("Show 4"),
      button( id:="cb5", cls:="show-topSide")("Show 5"),
      button( id:="cb6", cls:="show-bottomSide")("Show 6")
  )

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

  def nextButton(): Unit = {
    import org.scalajs.jquery.jQuery
    jQuery("#cb" + cbnum).click() // buttons(0).
    cbnum += 1
    if (cbnum > 6) cbnum = 1
  }

  //@tailrec
  def startSpin(): Unit = {
    import scala.scalajs.js.timers._

    setTimeout(15000) { // note the absence of () =>
      nextButton
      startSpin()
    }
  }

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
    h2("What is Object Nirvana?"),
    div("Writing software in the coolest way possible."),
    div("Help us gather all the ways."),
    div(CsStyles.cubeContainer)(
      div(id:="box_cs", `class`:="show-frontSide")(
        figure(frontSide)(div(h2("Object Oriented"), oop)),
        figure(backSide)(div(h2("Functional Programming"), fp)),
        figure(rightSide)(div(h2("First Class"), fc)),
        figure(leftSide)(div(h2("Aspect Oriented"), aop)),
        figure(topSide)(div(h2("Cybersecurity"), sec)),
        figure(bottomSide)(div(h2("Nirvana"), oni))
      )
    ),
    div(CsStyles.sqContainer)(
      div(CsStyles.leftSq)(
        h3("Tell us what makes writing software better:"),
        TextInput.debounced(model, placeholder := "Enter your favorite software quality..."),
        p("Your quality: ", bind(model)),
        button(onclick := saveQuality _)("Submit Quality")
      ),
      div(CsStyles.middleSq)(
        h3("Submitted Qualities"),
        ol( repeat(serverQualities)(sq =>
          li(id:=sq.get.id)(div(data.oid := sq.get.id, onclick := getSqDetails(sq.get.id.toString) _)(sq.get.desc)).render))
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
    div( id:="options") (
      p(onclick := startSpin _)("start spin"),
      p( id:="show-buttons")(
          buttons
      )
    ),
    script("""
      var init = function() {
        var box = document.querySelector('.CsStyles-cubeContainer').children[0],
          showPanelButtons = document.querySelectorAll('#show-buttons button');
        var panelClassName = 'show-frontSide';
        var figures = box.children;

        //alert('figure has ' + figures.length);
        var clearFigs = function() {
          for (var i=0; i<figures.length; i++) {
            figures[i].removeClassName( 'CsStyles-near' );
            //figures[i].addClassName( 'CsStyles-far' );
            //alert('adding');
          }
        }

        var onButtonClick = function( event ){
          clearFigs();
          box.removeClassName( panelClassName );
          panelClassName = event.target.className;
          var sideName = panelClassName.substring(5, panelClassName.length);
          //alert('pancl class name = ' + panelClassName + ' side name ' + sideName);
          window.setTimeout(function() {
            box.addClassName( panelClassName );
            // find side and add near
            $('.CsStyles-'+sideName).addClass('CsStyles-near');
          }, 2000);
        };

        for (var i=0, len = showPanelButtons.length; i < len; i++) {
          showPanelButtons[i].addEventListener( 'click', onButtonClick, false);
        }
      };
      window.addEventListener( 'DOMContentLoaded', init, false);
      
      // ======================= DOM Utility Functions from PastryKit =============================== //
      
      // Sure, we could use jQuery or XUI for these, 
      // but these are concise and will work with plain vanilla JS
      
      Element.prototype.hasClassName = function (a) {
          return new RegExp("(?:^|\\s+)" + a + "(?:\\s+|$)").test(this.className);
      };
      
      Element.prototype.addClassName = function (a) {
          if (!this.hasClassName(a)) {
              this.className = [this.className, a].join(" ");
          }
      };
      
      Element.prototype.removeClassName = function (b) {
          if (this.hasClassName(b)) {
              var a = this.className;
              this.className = a.replace(new RegExp("(?:^|\\s+)" + b + "(?:\\s+|$)", "g"), " ");
          }
      };
      
      Element.prototype.toggleClassName = function (a) {
        this[this.hasClassName(a) ? "removeClassName" : "addClassName"](a);
      };

      /* Modernizr 2.0.6 (Custom Build) | MIT & BSD
       * Build: http://www.modernizr.com/download/#-csstransforms3d-iepp-cssclasses-prefixed-teststyles-testprop-testallprops-prefixes-domprefixes-load
       */
      ;window.Modernizr=function(a,b,c){
        function C(a,b){
          var c=a.charAt(0).toUpperCase()+a.substr(1),d=(a+" "+o.join(c+" ")+c).split(" ");return B(d,b)}
          function B(a,b){for(var d in a)if(k[a[d]]!==c)return b=="pfx"?a[d]:!0;return!1}
          function A(a,b){return!!~(""+a).indexOf(b)}
          function z(a,b){return typeof a===b}function y(a,b){return x(n.join(a+";")+(b||""))}
          function x(a){k.cssText=a}var d="2.0.6",e={},f=!0,g=b.documentElement,h=b.head||b.getElementsByTagName("head")[0],i="modernizr",j=b.createElement(i),k=j.style,l,m=Object.prototype.toString,n=" -webkit- -moz- -o- -ms- -khtml- ".split(" "),o="Webkit Moz O ms Khtml".split(" "),p={},q={},r={},s=[],t=function(a,c,d,e){var f,h,j,k=b.createElement("div");if(parseInt(d,10))while(d--)j=b.createElement("div"),j.id=e?e[d]:i+(d+1),k.appendChild(j);f=["&shy;","<style>",a,"</style>"].join(""),k.id=i,k.innerHTML+=f,g.appendChild(k),h=c(k,a),k.parentNode.removeChild(k);return!!h},u,v={}.hasOwnProperty,w;!z(v,c)&&!z(v.call,c)?w=function(a,b){return v.call(a,b)}:w=function(a,b){return b in a&&z(a.constructor.prototype[b],c)};var D=function(a,c){var d=a.join(""),f=c.length;t(d,function(a,c){var d=b.styleSheets[b.styleSheets.length-1],g=d.cssRules&&d.cssRules[0]?d.cssRules[0].cssText:d.cssText||"",h=a.childNodes,i={};while(f--)i[h[f].id]=h[f];e.csstransforms3d=i.csstransforms3d.offsetLeft===9},f,c)}([,["@media (",n.join("transform-3d),("),i,")","{#csstransforms3d{left:9px;position:absolute}}"].join("")],[,"csstransforms3d"]);p.csstransforms3d=function(){var a=!!B(["perspectiveProperty","WebkitPerspective","MozPerspective","OPerspective","msPerspective"]);a&&"webkitPerspective"in g.style&&(a=e.csstransforms3d);return a};for(var E in p)w(p,E)&&(u=E.toLowerCase(),e[u]=p[E](),s.push((e[u]?"":"no-")+u));x(""),j=l=null,a.attachEvent&&function(){var a=b.createElement("div");a.innerHTML="<elem></elem>";return a.childNodes.length!==1}()&&function(a,b){function s(a){var b=-1;while(++b<g)a.createElement(f[b])}a.iepp=a.iepp||{};var d=a.iepp,e=d.html5elements||"abbr|article|aside|audio|canvas|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video",f=e.split("|"),g=f.length,h=new RegExp("(^|\\s)("+e+")","gi"),i=new RegExp("<(/*)("+e+")","gi"),j=/^\s*[\{\}]\s*$/,k=new RegExp("(^|[^\\n]*?\\s)("+e+")([^\\n]*)({[\\n\\w\\W]*?})","gi"),l=b.createDocumentFragment(),m=b.documentElement,n=m.firstChild,o=b.createElement("body"),p=b.createElement("style"),q=/print|all/,r;d.getCSS=function(a,b){if(a+""===c)return"";var e=-1,f=a.length,g,h=[];while(++e<f){g=a[e];if(g.disabled)continue;b=g.media||b,q.test(b)&&h.push(d.getCSS(g.imports,b),g.cssText),b="all"}return h.join("")},d.parseCSS=function(a){var b=[],c;while((c=k.exec(a))!=null)b.push(((j.exec(c[1])?"\n":c[1])+c[2]+c[3]).replace(h,"$1.iepp_$2")+c[4]);return b.join("\n")},d.writeHTML=function(){var a=-1;r=r||b.body;while(++a<g){var c=b.getElementsByTagName(f[a]),d=c.length,e=-1;while(++e<d)c[e].className.indexOf("iepp_")<0&&(c[e].className+=" iepp_"+f[a])}l.appendChild(r),m.appendChild(o),o.className=r.className,o.id=r.id,o.innerHTML=r.innerHTML.replace(i,"<$1font")},d._beforePrint=function(){p.styleSheet.cssText=d.parseCSS(d.getCSS(b.styleSheets,"all")),d.writeHTML()},d.restoreHTML=function(){o.innerHTML="",m.removeChild(o),m.appendChild(r)},d._afterPrint=function(){d.restoreHTML(),p.styleSheet.cssText=""},s(b),s(l);d.disablePP||(n.insertBefore(p,n.firstChild),p.media="print",p.className="iepp-printshim",a.attachEvent("onbeforeprint",d._beforePrint),a.attachEvent("onafterprint",d._afterPrint))}(a,b),e._version=d,e._prefixes=n,e._domPrefixes=o,e.testProp=function(a){return B([a])},e.testAllProps=C,e.testStyles=t,e.prefixed=function(a){return C(a,"pfx")},g.className=g.className.replace(/\bno-js\b/,"")+(f?" js "+s.join(" "):"");return e}(this,this.document),function(a,b,c){function k(a){return!a||a=="loaded"||a=="complete"}function j(){var a=1,b=-1;while(p.length- ++b)if(p[b].s&&!(a=p[b].r))break;a&&g()}function i(a){var c=b.createElement("script"),d;c.src=a.s,c.onreadystatechange=c.onload=function(){!d&&k(c.readyState)&&(d=1,j(),c.onload=c.onreadystatechange=null)},m(function(){d||(d=1,j())},H.errorTimeout),a.e?c.onload():n.parentNode.insertBefore(c,n)}function h(a){var c=b.createElement("link"),d;c.href=a.s,c.rel="stylesheet",c.type="text/css";if(!a.e&&(w||r)){var e=function(a){m(function(){if(!d)try{a.sheet.cssRules.length?(d=1,j()):e(a)}catch(b){b.code==1e3||b.message=="security"||b.message=="denied"?(d=1,m(function(){j()},0)):e(a)}},0)};e(c)}else c.onload=function(){d||(d=1,m(function(){j()},0))},a.e&&c.onload();m(function(){d||(d=1,j())},H.errorTimeout),!a.e&&n.parentNode.insertBefore(c,n)}function g(){var a=p.shift();q=1,a?a.t?m(function(){a.t=="c"?h(a):i(a)},0):(a(),j()):q=0}function f(a,c,d,e,f,h){function i(){!o&&k(l.readyState)&&(r.r=o=1,!q&&j(),l.onload=l.onreadystatechange=null,m(function(){u.removeChild(l)},0))}var l=b.createElement(a),o=0,r={t:d,s:c,e:h};l.src=l.data=c,!s&&(l.style.display="none"),l.width=l.height="0",a!="object"&&(l.type=d),l.onload=l.onreadystatechange=i,a=="img"?l.onerror=i:a=="script"&&(l.onerror=function(){r.e=r.r=1,g()}),p.splice(e,0,r),u.insertBefore(l,s?null:n),m(function(){o||(u.removeChild(l),r.r=r.e=o=1,j())},H.errorTimeout)}function e(a,b,c){var d=b=="c"?z:y;q=0,b=b||"j",C(a)?f(d,a,b,this.i++,l,c):(p.splice(this.i++,0,a),p.length==1&&g());return this}function d(){var a=H;a.loader={load:e,i:0};return a}var l=b.documentElement,m=a.setTimeout,n=b.getElementsByTagName("script")[0],o={}.toString,p=[],q=0,r="MozAppearance"in l.style,s=r&&!!b.createRange().compareNode,t=r&&!s,u=s?l:n.parentNode,v=a.opera&&o.call(a.opera)=="[object Opera]",w="webkitAppearance"in l.style,x=w&&"async"in b.createElement("script"),y=r?"object":v||x?"img":"script",z=w?"img":y,A=Array.isArray||function(a){return o.call(a)=="[object Array]"},B=function(a){return Object(a)===a},C=function(a){return typeof a=="string"},D=function(a){return o.call(a)=="[object Function]"},E=[],F={},G,H;H=function(a){function f(a){var b=a.split("!"),c=E.length,d=b.pop(),e=b.length,f={url:d,origUrl:d,prefixes:b},g,h;for(h=0;h<e;h++)g=F[b[h]],g&&(f=g(f));for(h=0;h<c;h++)f=E[h](f);return f}function e(a,b,e,g,h){var i=f(a),j=i.autoCallback;if(!i.bypass){b&&(b=D(b)?b:b[a]||b[g]||b[a.split("/").pop().split("?")[0]]);if(i.instead)return i.instead(a,b,e,g,h);e.load(i.url,i.forceCSS||!i.forceJS&&/css$/.test(i.url)?"c":c,i.noexec),(D(b)||D(j))&&e.load(function(){d(),b&&b(i.origUrl,h,g),j&&j(i.origUrl,h,g)})}}function b(a,b){function c(a){if(C(a))e(a,h,b,0,d);else if(B(a))for(i in a)a.hasOwnProperty(i)&&e(a[i],h,b,i,d)}var d=!!a.test,f=d?a.yep:a.nope,g=a.load||a.both,h=a.callback,i;c(f),c(g),a.complete&&b.load(a.complete)}var g,h,i=this.yepnope.loader;if(C(a))e(a,0,i,0);else if(A(a))for(g=0;g<a.length;g++)h=a[g],C(h)?e(h,0,i,0):A(h)?H(h):B(h)&&b(h,i);else B(a)&&b(a,i)},H.addPrefix=function(a,b){F[a]=b},H.addFilter=function(a){E.push(a)},H.errorTimeout=1e4,b.readyState==null&&b.addEventListener&&(b.readyState="loading",b.addEventListener("DOMContentLoaded",G=function(){b.removeEventListener("DOMContentLoaded",G,0),b.readyState="complete"},0)),a.yepnope=d()}(this,this.document),Modernizr.load=function(){yepnope.apply(window,[].slice.call(arguments,0))};
    """)
  )

  override def getTemplate: Modifier = content

  override def renderChild(view: View): Unit = {}
}