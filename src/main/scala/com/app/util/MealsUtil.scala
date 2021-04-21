package com.app.util

import com.app.model.Meal
import com.app.to.MealTo

import java.time.{LocalDate, LocalTime}

class MealsUtil {

  val dateTimeUtil = new DateTimeUtil

  def getTos(meals: List[Meal], caloriesPerDay: Int): List[MealTo] = {
    filterByPredicate(meals, caloriesPerDay, _ => true)
  }

  def getFilteredTos(meals: List[Meal], caloriesPerDay: Int, startTime: LocalTime, endTime: LocalTime): List[MealTo] = {
    filterByPredicate(meals, caloriesPerDay, meal => dateTimeUtil.isBetweenHalfOpen(meal.getTime, startTime, endTime))
  }

  def filterByPredicate(meals: List[Meal], caloriesPerDay: Int, f: Meal => Boolean): List[MealTo] = {
    val caloriesSumByDate: Map[LocalDate, Int] = meals.groupBy(_.getDate)
      .map { x => (x._1, x._2.map(meal => meal.calories).sum) }

    meals.filter(f).map {
      meal => createTo(meal, caloriesSumByDate(meal.getDate) > caloriesPerDay)
    }
  }

  def createTo(meal: Meal, excess: Boolean): MealTo = {
    MealTo(meal.id, meal.dateTime, meal.description, meal.calories, excess)
  }
}
