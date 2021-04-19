package com.app.util

import com.app.model.Meal
import com.app.to.MealTo

import java.time.LocalDate

abstract class MealsUtil() {

  def getTos(): List[MealTo] = {
    ???
  }

  def getFilteredTos(): List[MealTo] = {
    ???
  }

  def filterByPredicate(meals: List[Meal], caloriesPerDay: Int, f: Meal => Boolean): List[MealTo] = {
    val caloriesSumByDate: Map[LocalDate, Int] = meals.groupBy(_.getDate)
      .map { x => (x._1, x._2.map(meal => meal.calories).sum) }

    meals.filter(f).map {
      meal => createTo(meal, caloriesSumByDate(meal.getDate) > caloriesPerDay)
    }
  }

  def createTo(meal: Meal, excess: Boolean): MealTo = {
    MealTo(meal._id, meal.dateTime, meal.description, meal.calories, excess)
  }
}
