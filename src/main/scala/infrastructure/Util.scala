package infrastructure

import scala.collection.immutable.HashMap

object Util {

  implicit class BranchUtil(branch: Branch) {
    def getWeight: Long = 100*branch.fullYear + branch.releaseNum
  }

  implicit class FullAppVersionUtil(fav: FullAppVersion) {

    def isMain = fav.toString=="1.0.0-SNAPSHOT"

    def weightPriorityMap = HashMap(

    )

    def getWeight: Long =
      100*fav.branch.getWeight +
        1*fav.version
  }

}
