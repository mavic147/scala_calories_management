package com.app.web.controllers.user

import com.app.dao.UserDaoImpl

case class AbstractUserController() {

  val userDao: UserDaoImpl = UserDaoImpl()

//  private val log = LoggerFactory.getLogger()
}
