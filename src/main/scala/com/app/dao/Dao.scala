package com.app.dao

import com.app.model.{Meal, User}
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.result

import java.time.LocalDateTime
import scala.concurrent.Future

trait MealDao {

  def getOne(id: ObjectId, userId: ObjectId): Unit
  def delete(id: ObjectId, userId: ObjectId): Future[result.DeleteResult]
  def getAll(userId: ObjectId): Unit
  def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: ObjectId): List[Meal]
  def create(meal: Meal): Unit
  def update(meal: Meal, userId: ObjectId): Future[Meal]
}

trait UserDao {

  def getAll: Future[Seq[User]]
  def getByEmail(email: String): Future[Seq[User]]
  def getOne(id: Int): Future[Seq[User]]
  def delete(id :Int): Future[result.DeleteResult]
  def update(user: User):Future[User]
  def create(user: User): Future[result.InsertOneResult]

}

