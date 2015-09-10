package infrastructure

case class Branch(shortYear: Int, releaseNum: Int) {
  val fullYear = 2000 + shortYear
  override def toString = formatNormal
  def formatNormal = s"$fullYear.$releaseNum"
  def formatShort = s"$shortYear.$releaseNum"
}
