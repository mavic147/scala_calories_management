package com.app.web.controllers.meal

import com.app.dao.MealDaoImpl
import org.slf4j.LoggerFactory

case class AbstractMealController() {

  val mealDao: MealDaoImpl = MealDaoImpl()

  private val log = LoggerFactory.getLogger(this.getClass)
}
