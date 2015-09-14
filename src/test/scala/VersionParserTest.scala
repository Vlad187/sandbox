import re.infrastructure._
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.util.{Failure, Try, Success}

class VersionParserTest extends FunSuite with BeforeAndAfter {

  val testBranch = AppBranch(AppYear(2015), AppRelease(17))
  val testAppVersion = AppVersion(5)

  def runParser(parseText: String): Try[FullAppVersion] = new VersionParser(parseText).expression.run()

  test("Check release parse") {
    assert(
      runParser("2015.17.5") ==
        Success(FullAppVersion(testBranch, testAppVersion, None))
    )
  }

  test("Check release parse") {
    assert(
      runParser("2015.kd7.6").isFailure
    )
  }

  test("Check snapshot parse") {
    assert(
      runParser("2015.17.5-SNAPSHOT") ==
        Success(FullAppVersion(testBranch, testAppVersion, Some(AppPostfix(AppPostfix.snapshotStr))))
    )
  }

  test("Check main parse") {
    assert(
      runParser("1.0.0-SNAPSHOT") match {
        case Success(fav) => fav.isMain
      }
    )
  }

  test("Extra symbols") {
    assert(
      runParser("2015.17.5-SO56Tdhfskljfl") ==
        Success(FullAppVersion(testBranch, testAppVersion, Some(AppPostfix("-SO56Tdhfskljfl"))))
    )
  }

}