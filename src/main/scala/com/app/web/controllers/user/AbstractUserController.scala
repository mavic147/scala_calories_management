package com.app.web.controllers.user

import com.app.dao.UserDaoImpl
import org.slf4j.LoggerFactory

case class AbstractUserController() {

  val userDao: UserDaoImpl = UserDaoImpl()

  private val log = LoggerFactory.getLogger(this.getClass)
}
