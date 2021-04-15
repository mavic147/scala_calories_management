package com.app.dao
import com.app.model.Meal

import java.time.LocalDateTime

case class MealDaoImpl() extends MealDao {
  override def getOne(id: Int, userId: Int): Meal = ???

  override def delete(id: Int, userId: Int): Unit = ???

  override def getAll(userId: Int): List[Meal] = ???

  override def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: Int): List[Meal] = ???

  override def create(meal: Meal, userId: Int): Meal = ???

  override def update(meal: Meal, userId: Int): Unit = ???
}
