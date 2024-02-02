package soc.user.router

import cats.effect.Async
import cats.implicits.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import org.http4s.HttpRoutes
import org.http4s.circe.*
import org.http4s.dsl.Http4sDsl
import soc.common.utils.Injector
import soc.user.service.UserService

object UserRouter {

  def userRoutes[F[_] : Async]: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl.*
    val injector = Injector.get
    val userService = injector.getInstance(classOf[UserService])

    HttpRoutes.of[F] {
      case GET -> Root / "user" / "all" =>
        userService.getUsers[F]().flatMap { users => Ok(users.asJson) }
    }
  }
}
