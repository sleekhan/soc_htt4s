package soc.user.entity

import slick.jdbc.H2Profile.api.*
import slick.lifted.Tag

class User(tag: Tag) extends Table[(Int, String, String)](tag, "member") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def email = column[String]("email")

  def * = (id, name, email)
}

lazy val userQuery = TableQuery[User]
