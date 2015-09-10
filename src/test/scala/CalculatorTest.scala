import org.scalatest._

import scala.util.Success

class CalculatorTest extends FunSuite {

  test("Basic check") {
    assert( new Calculator("1+1").InputLine.run() == Success(2) )
  }

  test("Operation priority check") {
    assert( new Calculator("1+2*3").InputLine.run() == Success(7) )
  }

}
