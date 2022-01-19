ThisBuild / version := "1.0"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "hello-scala",
    organization := "me.alstepan",
    assembly / mainClass := Some("me.alstepan.Main")
  )

ThisBuild / assemblyMergeStrategy := {
  case "application.conf" => MergeStrategy.concat
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

libraryDependencies ++= Seq(
  // cats
  "org.typelevel" %% "cats-core"            % "2.7.0",
  "org.typelevel" %% "cats-effect"          % "3.3.4",
  // logging
  "ch.qos.logback" % "logback-classic"      % "1.2.10",
  "org.typelevel" %% "log4cats-core"        % "2.1.1",
  "org.typelevel" %% "log4cats-slf4j"       % "2.1.1",
  // http4s
  "org.http4s"    %% "http4s-dsl"           % "0.23.7",
  "org.http4s"    %% "http4s-circe"         % "0.23.7",
  "org.http4s"    %% "http4s-blaze-server"  % "0.23.7",
)