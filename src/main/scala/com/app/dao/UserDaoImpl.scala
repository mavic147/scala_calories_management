package com.app.dao
import com.app.model.User

case class UserDaoImpl() extends UserDao {
  override def getAll: List[User] = ???

  override def getByEmail(email: String): User = ???

  override def getOne(id: Int): User = ???

  override def delete(id: Int): Unit = ???

  override def update(user: User): Unit = ???

  override def create(user: User): User = ???
}
