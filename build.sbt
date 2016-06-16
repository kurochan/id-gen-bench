organization := "org.kurochan"
name := "id-gen-bench"
version := "0.0.1"
scalaVersion := "2.11.7"
scalacOptions += "-deprecation"

enablePlugins(JmhPlugin)

libraryDependencies ++= Seq(
  "commons-codec" % "commons-codec" % "1.9",
  "joda-time" % "joda-time" % "2.3"
)

scalariformSettings
