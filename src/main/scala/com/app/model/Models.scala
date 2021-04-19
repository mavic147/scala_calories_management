package com.app.model

import com.app.model.Role.Role
import org.mongodb.scala.bson.ObjectId

import java.time.{LocalDate, LocalDateTime, LocalTime}
import java.util.Date

object Meal {
  def apply(dateTime: LocalDateTime, description: String, calories: Int, userId: ObjectId): Meal =
    Meal(new ObjectId(), dateTime, description, calories, userId)
}

case class Meal(_id: ObjectId, dateTime: LocalDateTime, description: String, calories: Int, userId: ObjectId) {

  override def toString: String = s"Meal {id= ${_id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}}"

  def getDate: LocalDate = {
    this.dateTime.toLocalDate
  }

  def getTime: LocalTime = {
    this.dateTime.toLocalTime
  }
}

object User {
  def apply(name: String, email: String, password: String, caloriesPerDay: Int, registered: Date, roles: List[Role]): User =
    User(new ObjectId(), name, email, password, caloriesPerDay, registered, roles)
}

case class User(_id:ObjectId, name:String, email:String, password:String, caloriesPerDay:Int, registered:Date, roles: List[Role]) {

  override def toString: String = s"User {id= ${_id}, name= ${name}, email= ${email}, caloriesPerDay= ${caloriesPerDay}}"
}

object Role extends Enumeration {
  type Role = Value
  val User, Admin = Value
}