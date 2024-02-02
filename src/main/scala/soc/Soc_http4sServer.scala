package soc

import cats.effect.Async
import com.comcast.ip4s.*
import fs2.io.net.Network
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.Router
import org.http4s.server.middleware.Logger
import slick.jdbc.H2Profile.api.*
import soc.common.config.Properties
import soc.user.router.UserRouter

object Db {
  private val connection = Database.forURL(
    url = Properties.databaseUrl,
    user = Properties.databaseUser,
    password = Properties.databasePassword,
    driver = Properties.databaseDriver
  )

  def getConnection: Database = connection
}

object Soc_http4sServer {
  def run[F[_] : Async : Network]: F[Nothing] = {
    val userRouter = UserRouter.userRoutes[F]
    val httpApp = Router("/" -> userRouter).orNotFound
    val finalHttpApp = Logger.httpApp(true, true)(httpApp)
    EmberServerBuilder.default[F]
      .withHost(ipv4"0.0.0.0")
      .withPort(Port.fromInt(Properties.serverPort).get)
      .withHttpApp(finalHttpApp)
      .build
  }.useForever
}
