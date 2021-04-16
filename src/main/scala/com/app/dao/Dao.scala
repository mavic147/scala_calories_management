package com.app.dao

import com.app.model.{Meal, User}
import org.mongodb.scala.bson.ObjectId

import java.time.LocalDateTime

trait MealDao {

  def getOne(id: Int, userId: Int): Meal
  def delete(id: Int, userId: Int): Unit
  def getAll(userId: Int): List[Meal]
  def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: Int): List[Meal]
  def create(meal: Meal, userId: ObjectId): Meal
  def update(meal: Meal, userId: Int): Unit
}

trait UserDao {

  def getAll: List[User]
  def getByEmail(email: String): User
  def getOne(id: Int): User
  def delete(id :Int): Unit
  def update(user: User): Unit
  def create(user: User): User

}

