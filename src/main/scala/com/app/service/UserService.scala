package com.app.service

import com.app.dao.UserDaoImpl

case class UserService() {

  val userDaoImpl: UserDaoImpl.type = UserDaoImpl
}
