package re.infrastructure

import org.parboiled2._

case class AppYear(fullNum: Int) {
  private val (minYear, maxYear) = (2000,3000)
  fullNum match { case correct if correct.between(minYear, maxYear) || correct.equals(Const.) => }
}

case class AppBranch(fullYear: Int, releaseNum: Int) {

  import Util.IntUtil

  private val (minRelease, maxRelease) = (0,99)


  releaseNum match { case correct if correct.between(minRelease, maxRelease) && correct.equals(0) => }

  val shortYear = fullYear % 100

  override def toString = formatNormal
  def formatNormal = s"$fullYear.$releaseNum"
  def formatShort = s"$shortYear.$releaseNum"

}

case class AppVersion(num: Int)
case class

case class FullAppVersion(branch: AppBranch, version: AppVersion, postfix: Option[String]) {

  private val maxPostfixLength = 100

  postfix match { case correct if correct.getOrElse("").length < maxPostfixLength => }

  override def toString = s"${branch.formatShort}.$version${postfix.getOrElse("")}"

  def isSnapshot = postfix match {
    case Some(Const.snapshotPostfix) => true
    case _ => false
  }

  def isMain = toString match {
    case "1.0.0-SNAPSHOT" => true
    case _ => false
  }

}

class VersionParser(val input: ParserInput) extends Parser {

  import CharPredicate.Digit

  private val WhiteSpaceChar = CharPredicate(" \n\r\t\f")

  def yearFull = rule { capture(4.times(Digit)) ~> (_.toInt) }

  def release = rule { capture(2.times(Digit)) ~> (_.toInt) }

  def appVersion = rule { capture((1 to 2).times(Digit)) ~> (_.toInt) }

  def branch: Rule1[AppBranch] = rule { yearFull ~ '.' ~ release ~> AppBranch }

  def postFix = rule { optional(capture(oneOrMore(CharPredicate.Visible))) }

  def fullAppVersion: Rule1[FullAppVersion] = rule { branch ~ '.' ~ appVersion ~ postFix ~> FullAppVersion }

  def expression: Rule1[FullAppVersion] = rule { zeroOrMore(WhiteSpaceChar) ~ fullAppVersion ~ zeroOrMore(WhiteSpaceChar) ~ EOI }

}

object Const {

  val snapshotPostfix = "-SNAPSHOT"
  val mainFullVersion = FullAppVersion(AppBranch(1, 0), 0, Some(snapshotPostfix))

}