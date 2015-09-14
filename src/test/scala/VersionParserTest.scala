import re.infrastructure.{AppBranch, FullAppVersion, VersionParser}
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.util.{Try, Success}

class VersionParserTest extends FunSuite with BeforeAndAfter {

  val testBranch = AppBranch(2015, 17)
  val testAppVersion = 5

  def runParser(parseText: String): Try[FullAppVersion] = new VersionParser(parseText).expression.run()

  test("Check release parse") {
    assert(
      runParser("2015.17.5") == Success(FullAppVersion(testBranch, testAppVersion, None))
    )
  }

  test("Check snapshot parse") {
    assert(
      runParser("2015.17.5-SNAPSHOT") == Success(FullAppVersion(testBranch, testAppVersion, Some("-SNAPSHOT")))
    )
  }

  test("Check main parse") {
    assert(
      runParser("1.0.0-SNAPSHOT") match { case Success(fav) => fav.isMain }
    )
  }

  test("Extra symbols") {
    assert(
      runParser("2015.17.5-SO56Tdhfskljfl") match { case Success(fav) => print(fav); true }
    )
  }

}