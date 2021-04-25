package com.app.dao

import com.app.model.{Meal, User}
import org.mongodb.scala.result

import java.time.LocalDateTime
import scala.concurrent.Future

trait MealDao {

  def getOne(id: String, userId: String): Future[Seq[Meal]]
  def delete(id: String, userId: String): Future[result.DeleteResult]
  def getAll(userId: String): Future[Seq[Meal]]
  def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: String): Future[Seq[Meal]]
  def create(meal: Meal): Future[result.InsertOneResult]
  def update(meal: Meal, userId: String): Future[Meal]
}

trait UserDao {

  def getAll: Future[Seq[User]]
  def create(user: User): Future[result.InsertOneResult]
}

