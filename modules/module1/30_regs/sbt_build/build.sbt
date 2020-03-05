scalaVersion := "0.22.0-RC1"
name := "regs"
libraryDependencies ++= Seq(
    "com.lihaoyi" %% "requests" % "0.5.1",
    "com.lihaoyi" %% "ammonite-ops" % "2.0.4",
    "org.scala-lang.modules" % "scala-xml_2.13" % "2.0.0-M1",
).map(_.withDottyCompat(scalaVersion.value))
