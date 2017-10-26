name := "locktest"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= {
  val scoptV = "3.7.0"
  val ammoniteV = "COMMIT-7784517"
  val sczversion = "7.2.16"

  Seq(
    "com.github.scopt" %% "scopt" % scoptV,
    "org.scalaz" %% "scalaz-core" % sczversion,
    "org.scalaz" %% "scalaz-concurrent" % sczversion,
    "com.lihaoyi" % "ammonite-repl" % ammoniteV % "test" cross CrossVersion.full,
    "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
  )
}

mainClass in (Compile, packageBin) := Some("company.code.core.Core1.main")

initialCommands in (Test, console) := """ammonite.repl.Main.run("")"""
