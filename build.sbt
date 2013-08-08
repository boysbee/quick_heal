organization := "scala"

name := "quick_heal"

version := "1.0"

scalaVersion := "2.10.2"

unmanagedBase <<= baseDirectory { base => base / "lib_managed" }

unmanagedJars in Compile <++= baseDirectory map { base =>
    val baseDirectories = (base / "lib_managed")
    val customJars = (baseDirectories ** "*.jar")
    customJars.classpath
}


resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "1.9.1" % "test",
	"org.scala-lang" % "scala-swing" % "2.10.2"
)

