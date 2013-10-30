name := "Grinder"

version := "1.0"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.9.5",
  "org.specs2" %% "specs2" % "1.12.3" % "test"
)
