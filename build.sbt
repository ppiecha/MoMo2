ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka"     %% "akka-actor-typed"              % "2.6.21",
  "ch.qos.logback"         % "logback-classic"               % "1.4.14",
  "com.github.pureconfig" %% "pureconfig"                    % "0.17.6",
  "eu.timepit"            %% "refined"                       % "0.11.1",
  "eu.timepit"            %% "refined-pureconfig"            % "0.11.1", // optional, JVM-only
  "com.typesafe.akka"     %% "akka-actor-testkit-typed"      % "2.8.5"            % Test,
  "org.scalatest"         %% "scalatest"                     % "3.2.15"           % Test,
  "org.scalactic"         %% "scalactic"                     % "3.2.16",
  "org.scala-lang"         % "scala-reflect"                 % "2.13.10",
  "org.scala-lang"         % "scala-compiler"                % "2.13.10",
  "org.scala-lang"        %% "toolkit"                       % "0.1.7",
  "com.github.scopt"      %% "scopt"                         % "4.1.0"
)

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

lazy val root = (project in file("."))
  .settings(
    name := "MoMo2"
  )
