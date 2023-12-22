ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.5",
  //"ch.qos.logback" % "logback-classic" % "1.2.11",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % "2.8.5" % Test,
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.scalactic" %% "scalactic" % "3.2.16",
  "org.scala-lang" % "scala-reflect" % "2.13.10",
  "org.scala-lang" % "scala-compiler" % "2.13.10",
  "io.circe" %% "circe-yaml" % "0.14.2",
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5",
  "io.circe" %% "circe-generic-extras" % "0.14.3",
  "io.circe" %% "circe-parser" % "0.14.5",
  "org.scala-lang" %% "toolkit" % "0.1.7",
  "com.github.scopt" %% "scopt" % "4.1.0"
)

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

lazy val root = (project in file("."))
  .settings(
    name := "MoMo2"
  )
