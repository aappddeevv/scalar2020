import mill._, scalalib._

object usregs extends ScalaModule { 
  def millSourcePath = ammonite.ops.pwd
  def scalaVersion = "0.22.0-RC1"
  def ivyDeps = Agg(
    ivy"com.lihaoyi::requests:0.5.1".withDottyCompat(scalaVersion()),
    ivy"com.lihaoyi::ammonite-ops:2.0.4".withDottyCompat(scalaVersion()),
    ivy"org.scala-lang.modules:scala-xml_2.13:2.0.0-M1".withDottyCompat(scalaVersion()),
  )
  def scalacOptions = Seq("-indent", "-Yindent-colons", "-strict")
}
