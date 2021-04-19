package com.app.service

import com.app.dao.MealDaoImpl

case class MealService() {

  val mealDaoImpl: MealDaoImpl.type = MealDaoImpl

}
