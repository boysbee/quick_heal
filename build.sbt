import AssemblyKeys._

seq(assemblySettings: _*)

name := "quick_heal"

version := "1.0"

scalaVersion := "2.10.2"

organization := "th.co.truecorp.csm"

unmanagedBase <<= baseDirectory { base => base / "lib" }

unmanagedJars in Compile <++= baseDirectory map { base =>
    val baseDirectories = (base / "lib_managed")
    val customJars = (baseDirectories ** "*.jar")
    customJars.classpath
}



libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "1.9.1" % "test",
	"org.scala-lang" % "scala-swing" % "2.10.1"
)

resolvers ++= Seq(
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

assemblySettings
