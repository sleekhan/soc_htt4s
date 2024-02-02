val http4sVersion = "0.23.23"
val circeVersion = "0.14.6"
val munitVersion = "0.7.29"
val logbackVersion = "1.4.14"
val munitCatsEffectVersion = "1.0.7"
val slickVersion = "3.5.0-M5"
val slf4jVersion = "2.0.11"
val postgresqlVersion = "42.7.1"
val circerVersion = "0.14.6"
val guiceVersion = "7.0.0"
val typesafeConfigVersion = "1.4.3"

lazy val root = (project in file("."))
  .settings(
    organization := "org.ryan.app",
    name := "soc_http4s",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.3.1",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion,
      "io.circe" %% "circe-generic" % circerVersion,
      "io.circe" %% "circe-literal" % circerVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "net.codingwell" %% "scala-guice" % guiceVersion,
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
      "org.postgresql" % "postgresql" % postgresqlVersion,
      "com.typesafe" % "config" % typesafeConfigVersion,
      "ch.qos.logback" % "logback-classic" % logbackVersion,
      "org.slf4j" % "slf4j-api" % slf4jVersion,
      "org.scalameta" %% "munit" % munitVersion % Test,
      "org.typelevel" %% "munit-cats-effect-3" % munitCatsEffectVersion % Test,
    ),
    assembly / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case x => (assembly / assemblyMergeStrategy).value.apply(x)
    }
  )
