package re.infrastructure

case class AppBranch(year: AppYear, release: AppRelease) {

  private val stringSeparator = "."
  def isMain = year.isMain && release.isMain

  def formatNormal = Stream(year.fullNum, release.num).mkString(stringSeparator)
  def formatShort = Stream(year.shortNum, release.num).mkString(stringSeparator)

}
