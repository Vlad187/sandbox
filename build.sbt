import sbt.Keys._

name := "io.sandbox"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.parboiled" %% "parboiled" % "2.1.0",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "com.cronutils" % "cron-utils" % "3.1.0"
)
