package infrastructure

case class FullAppVersion(branch: Branch, version: Int, isSnapshot: Boolean) {
  override def toString = s"${branch.formatShort}.$version" + {
    isSnapshot match {
      case true => "-SNAPSHOT"
      case false => ""
    }
  }
}
