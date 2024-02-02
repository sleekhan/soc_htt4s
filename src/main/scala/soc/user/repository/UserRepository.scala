package soc.user.repository

import cats.effect.Async
import com.google.inject.ImplementedBy
import slick.jdbc.H2Profile.api.*
import soc.Db
import soc.user.dto.UserDTO
import soc.user.entity.userQuery

import scala.concurrent.ExecutionContext.Implicits.global

@ImplementedBy(classOf[UserRepositoryImpl])
trait UserRepository {
  def findUsers[F[_] : Async](): F[List[UserDTO]]
}

class UserRepositoryImpl extends UserRepository {
  def findUsers[F[_] : Async](): F[List[UserDTO]] = {
    val db = Db.getConnection
    val users = for {
      rows <- db.run(userQuery.result)
    } yield rows.map {
      case (id, name, email) => UserDTO(id, name, email)
    }.toList
    Async[F].fromFuture(Async[F].delay(users))
  }
}