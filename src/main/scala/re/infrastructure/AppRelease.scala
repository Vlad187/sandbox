package re.infrastructure

import Util.IntUtil

case class AppRelease(num: Int) {

  import AppRelease._

  def isMain = num.equals(mainReleaseNum)

  num match {
    case correct
      if correct.between(minRelease, maxRelease)
        || correct.equals(mainReleaseNum) =>
  }

}

object AppRelease {
  val (minRelease, maxRelease) = (0,99)
  val mainReleaseNum = 0
}
