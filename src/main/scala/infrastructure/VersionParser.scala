package infrastructure

import org.parboiled2._

class VersionParser(val input: ParserInput) extends Parser {

  import CharPredicate.Digit

  def year = rule { capture(4.times(Digit)) ~> (_.toInt) }

  def release = rule { capture(2.times(Digit)) ~> (_.toInt) }

  def appVersion = rule { capture((1 to 2).times(Digit)) ~> (_.toInt) }

  //Parser doesn't handle placeholders... sometimes.
  def branch = rule { (year ~ '.' ~ release).asInstanceOf[Rule2[Int, Int]] ~> Branch }

  def fullAppVersion = rule { (branch ~ '.' ~ appVersion).asInstanceOf[Rule2[Branch, Int]] ~> ( (x,y) => FullAppVersion(x, y, true)) }

  def expression = rule { fullAppVersion ~ EOI }

}