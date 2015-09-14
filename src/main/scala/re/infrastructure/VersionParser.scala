package re.infrastructure

import org.parboiled2._
import Util.IntUtil

object AppRelease {
  val (minRelease, maxRelease) = (0,99)
  val mainReleaseNum = 0
}
case class AppRelease(num: Int) {

  import AppRelease._

  def isMain = num.equals(mainReleaseNum)

  num match {
    case correct
      if correct.between(minRelease, maxRelease)
      || correct.equals(mainReleaseNum) =>
  }

}

case class AppBranch(year: AppYear, release: AppRelease) {

  private val stringSeparator = "."
  def isMain = year.isMain && release.isMain

  def formatNormal = Stream(year.fullNum, release.num).mkString(stringSeparator)
  def formatShort = Stream(year.shortNum, release.num).mkString(stringSeparator)

}

case class AppVersion(num: Int) {

  num match { case correct if correct >= 0 => }

}

object AppPostfix {
  val snapshotStr = "-SNAPSHOT"
}
case class AppPostfix(str: String) {

  import AppPostfix._

  def isSnapshot = str.equals(snapshotStr)
  
  private val maxPostfixLength = 100

  def validate = str match {
    case correct if correct.length < maxPostfixLength => true
    case _ => false
  }

}

object FullAppVersion {
  val mainVersionNum = 0
}
case class FullAppVersion(branch: AppBranch, version: AppVersion, postfixOpt: Option[AppPostfix]) {

  import FullAppVersion._

  def isMain = branch.isMain && version.num.equals(mainVersionNum) && postfixOpt.nonEmpty && postfixOpt.get.isSnapshot

  def format = s"${branch.formatShort}.$version${postfixOpt.getOrElse("")}"

}

class VersionParser(val input: ParserInput) extends Parser {

  import CharPredicate.Digit

  private val WhiteSpaceChar = CharPredicate(" \n\r\t\f")

  def yearFull = rule {
    capture(4.times(Digit)|AppYear.mainYearNum.toString) ~> ( (s: String) => AppYear(s.toInt) )
  }

  def release = rule {
    capture(2.times(Digit)|AppRelease.mainReleaseNum.toString) ~> ( (s: String) => AppRelease(s.toInt) )
  }

  def appVersion = rule {
    capture((1 to 2).times(Digit)|FullAppVersion.mainVersionNum.toString) ~> ( (s: String) => AppVersion(s.toInt) )
  }

  def branch = rule {
    (yearFull ~ '.' ~ release) ~> (AppBranch(_,_))
  }

  def postfix = rule {
    capture(oneOrMore(CharPredicate.Visible)) ~> (AppPostfix(_))
  }

  def fullAppVersion: Rule1[FullAppVersion] = rule {
    (branch ~ '.' ~ appVersion ~ optional(postfix)) ~> (FullAppVersion(_,_,_))
  }

  def expression: Rule1[FullAppVersion] = rule {
    zeroOrMore(WhiteSpaceChar) ~ fullAppVersion ~ zeroOrMore(WhiteSpaceChar) ~ EOI
  }

}

object Const {

  import AppPostfix._

  val mainFullVersion = FullAppVersion(AppBranch(AppYear(1), AppRelease(0)), AppVersion(0), Some(AppPostfix(snapshotStr)))

}