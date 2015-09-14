package re.infrastructure

import Util.IntUtil

case class AppYear(fullNum: Int) {

  import AppYear._

  def isMain = fullNum.equals(mainYearNum)

  fullNum match {
    case correct
      if correct.between(minYear, maxYear)
      || isMain =>
  }

  val shortNum = isMain match {
    case true => fullNum
    case false => fullNum % 100
  }

}

object AppYear {

  val (minYear, maxYear) = (2000, 3000)
  val mainYearNum = 1

}

