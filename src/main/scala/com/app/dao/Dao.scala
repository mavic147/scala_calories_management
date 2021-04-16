package com.app.dao

import com.app.model.{Meal, User}
import org.mongodb.scala.result

import java.time.LocalDateTime
import scala.concurrent.Future

trait MealDao {

  def getOne(id: Int, userId: Int): Future[Seq[Meal]]
  def delete(id: Int, userId: Int): Future[result.DeleteResult]
  def getAll(userId: Int): Future[Seq[Meal]]
  def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: Int): List[Meal]
  def create(meal: Meal): Future[result.InsertOneResult]
  def update(meal: Meal, userId: Int): Future[Meal]
}

trait UserDao {

  def getAll: Future[Seq[User]]
  def getByEmail(email: String): Future[Seq[User]]
  def getOne(id: Int): Future[Seq[User]]
  def delete(id :Int): Future[result.DeleteResult]
  def update(user: User):Future[User]
  def create(user: User): Future[result.InsertOneResult]

}

