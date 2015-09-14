package re.infrastructure

import scala.collection.immutable.HashMap

object Util {

  implicit class BranchUtil(branch: AppBranch) {
    def getWeight: Long = 100*branch.year.fullNum + branch.release.num
  }

  implicit class FullAppVersionUtil(fav: FullAppVersion) {

    def isMain = fav.toString=="1.0.0-SNAPSHOT"

    def weightPriorityMap = HashMap(

    )

    def getWeight: Long =
      100*fav.branch.getWeight +
        1*fav.version.num
  }

  implicit class IntUtil(num: Int) {
    def between(leftLimit: Int, rightLimit: Int) = (num >= leftLimit) && (num <= rightLimit)
  }

  //TODO: try impilicits for constructing app case classes and parser fix
  /*implicit class TupleUtil() {

  }*/

}
