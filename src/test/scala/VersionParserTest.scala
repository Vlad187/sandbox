import infrastructure.{Branch, FullAppVersion, VersionParser}
import org.scalatest.FunSuite

import scala.util.Success

class VersionParserTest extends FunSuite {

  test("Check release parse") {
    val checkFav = FullAppVersion(Branch(2015,17),5,isSnapshot=false)
    val parseText = "2015.17.5"
    val parseResult = new VersionParser(parseText).expression.run()
    assert( parseResult == Success(checkFav) )
  }

  test("Check snapshot parse") {
    val checkFav = FullAppVersion(Branch(2015,17),5,isSnapshot=true)
    val parseText = "2015.17.5-SNAPSHOT"
    val parseResult = new VersionParser(parseText).expression.run()
    assert( parseResult == Success(checkFav) )
  }

  test("Check main parse") {

  }

  test("Extra symbols") {

  }

}
