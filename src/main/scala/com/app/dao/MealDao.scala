package com.app.dao

import com.app.model.Meal

import java.time.LocalDateTime

trait MealDao {

  def getOne(id: Int, userId: Int): Meal
  def delete(id: Int, userId: Int): Unit
  def getAll(userId: Int): List[Meal]
  def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: Int): List[Meal]
  def create(meal: Meal, userId: Int): Meal
  def update(meal: Meal, userId: Int): Unit
}
