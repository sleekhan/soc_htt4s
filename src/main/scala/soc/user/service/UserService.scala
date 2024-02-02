package soc.user.service

import cats.effect.Async
import com.google.inject.{ImplementedBy, Inject}
import soc.user.dto.UserDTO
import soc.user.repository.UserRepository

@ImplementedBy(classOf[UserServiceImpl])
trait UserService {
  def getUsers[F[_] : Async](): F[List[UserDTO]]
}

class UserServiceImpl @Inject()(userRepository: UserRepository) extends UserService {
  def getUsers[F[_] : Async](): F[List[UserDTO]] = {
    userRepository.findUsers[F]()
  }
}
