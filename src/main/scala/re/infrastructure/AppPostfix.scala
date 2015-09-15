package re.infrastructure

case class AppPostfix(str: String) {

  import AppPostfix._

  def isSnapshot = str.equals(snapshotStr)

  private val maxPostfixLength = 100

  def validate = str match {
    case correct if correct.length < maxPostfixLength => true
    case _ => false
  }

}

object AppPostfix {
  val snapshotStr = "-SNAPSHOT"
}