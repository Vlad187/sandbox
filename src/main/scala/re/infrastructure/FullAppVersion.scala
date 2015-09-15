package re.infrastructure

case class FullAppVersion(branch: AppBranch, version: AppVersion, postfixOpt: Option[AppPostfix]) {

  import FullAppVersion._

  def isMain = branch.isMain && version.num.equals(mainVersionNum) && postfixOpt.nonEmpty && postfixOpt.get.isSnapshot

  def format = s"${branch.formatShort}.$version${postfixOpt.getOrElse("")}"

}

object FullAppVersion {
  val mainVersionNum = 0
}