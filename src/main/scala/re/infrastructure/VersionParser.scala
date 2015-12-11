package re.infrastructure

import org.parboiled2._

//TODO: lazy validations after parsing
class VersionParser(val input: ParserInput) extends Parser {

  import CharPredicate.Digit

  private val WhiteSpaceChar = CharPredicate(" \n\r\t\f")

  def yearFull = rule {
    capture(4.times(Digit)|AppYear.mainYearNum.toString) ~> { AppYear apply _.toInt }
  }

  def release = rule {
    capture(2.times(Digit)|AppRelease.mainReleaseNum.toString) ~> { AppRelease apply _.toInt }
  }

  def appVersion = rule {
    capture((1 to 2).times(Digit)|FullAppVersion.mainVersionNum.toString) ~> { AppVersion apply _.toInt }
  }

  def branch = rule {
    { yearFull ~ '.' ~ release } ~> { (y: AppYear, r: AppRelease) => AppBranch.apply(y,r) }
  }

  def postfix = rule {
    capture(oneOrMore(CharPredicate.Visible)) ~> { AppPostfix(_) }
  }

  def fullAppVersion = rule {
    { branch ~ '.' ~ appVersion ~ optional(postfix) } ~> { (b: AppBranch, v: AppVersion, op: Option[AppPostfix]) => FullAppVersion(b,v,op) }
  }

  def expression: Rule1[FullAppVersion] = rule {
    zeroOrMore(WhiteSpaceChar) ~ fullAppVersion ~ zeroOrMore(WhiteSpaceChar) ~ EOI
  }

}

object Const {

  import AppPostfix._

  val mainFullVersion = FullAppVersion(AppBranch(AppYear(1), AppRelease(0)), AppVersion(0), Some(AppPostfix(snapshotStr)))

}