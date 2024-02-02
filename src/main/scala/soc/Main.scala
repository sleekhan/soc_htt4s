package soc

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  val run = Soc_http4sServer.run[IO]
