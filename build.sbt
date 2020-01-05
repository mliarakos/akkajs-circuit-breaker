import sbt.Keys.scalaVersion
import sbt.Keys.version

scalaVersion := "2.12.10"
scalacOptions ++= Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-feature",
  "-unchecked"
)

enablePlugins(ScalaJSPlugin)

organization := "com.github.mliarakos"
name := "akkajs-circuit-breaker"
version := "0.1.0"

libraryDependencies ++= Seq(
  "org.akka-js"   %%% "akkajsactor" % "2.2.6.1",
  "org.scalatest" %%% "scalatest"   % "3.0.8" % Test
)
