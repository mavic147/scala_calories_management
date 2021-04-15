package com.app.dao

import com.app.model.User

trait UserDao {

  def getAll: List[User]
  def getByEmail(email: String): User
  def getOne(id: Int): User
  def delete(id :Int): Unit
  def update(user: User): Unit
  def create(user: User): User

}
