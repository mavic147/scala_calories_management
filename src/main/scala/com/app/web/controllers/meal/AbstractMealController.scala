package com.app.web.controllers.meal

import com.app.dao.MealDaoImpl

case class AbstractMealController() {

  val mealDao: MealDaoImpl = MealDaoImpl()

//  private val log = LoggerFactory.getLogger()
}
