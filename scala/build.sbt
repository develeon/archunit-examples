val versions = new {
  val specs2     = "4.17.0"
  val cats       = "2.6.1"
  val catsEffect = "3.4.4"
  val fs2        = "2.5.10"
  val archUnit   = "1.0.1"
  val sl4fj      = "2.0.5"
}

val testDependencies = List(
  "org.specs2"          %% "specs2-core" % versions.specs2   % "it,test",
  "com.tngtech.archunit" % "archunit"    % versions.archUnit % "it, test",
  "com.tngtech.archunit" % "archunit-junit5"    % versions.archUnit % "it, test",
)

val cats = List(
  "org.typelevel" %% "cats-core"   % versions.cats,
  "org.typelevel" %% "cats-effect" % versions.catsEffect,
)

val fs2 = List(
  "co.fs2" %% "fs2-core" % versions.fs2,
  //  "co.fs2" %% "fs2-io" % versions.fs2,
  //  "co.fs2" %% "fs2-reactive-streams" % versions.fs2,
  //  "co.fs2" %% "fs2-scodec" % versions.fs2,
)

lazy val root = (project in file("."))
  .settings(
    name := "archunit-scala",
    description := "Examples of Archunit for Scala",
    version := "0.1.0",
    scalaVersion := "2.13.8"
  )
  .settings(
    libraryDependencies ++= Seq(
//          "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
//          "org.slf4j"               % "slf4j-api"                % versions.sl4fj % Provided,
//          "org.slf4j"               % "slf4j-nop"                % versions.sl4fj % Test,
    ) ++ testDependencies // ++ fs2 ++ cats
  )
