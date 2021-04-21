package com.app.model

import com.app.model.Role.Role
import org.bson.codecs.pojo.annotations.BsonId

import java.time.{LocalDate, LocalDateTime, LocalTime}
import java.util.Date

//object Meal {
//  def apply(dateTime: LocalDateTime, description: String, calories: Int, userId: ObjectId): Meal =
//    Meal(new ObjectId(), dateTime, description, calories, userId)
//
//}

case class Meal(@BsonId var id: String, dateTime: LocalDateTime, description: String, calories: Int, userId: String) {

  override def toString: String = s"Meal {id= ${id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}}"

  def idToInt = Map ("id" -> id.toInt)

  def userIdToInt = Map ("userId" -> userId.toInt)

  var idCounter = 100011

  def incrementId(): Int = {
    idCounter = idCounter + 1
    idCounter
  }

  def getDate: LocalDate = {
    this.dateTime.toLocalDate
  }

  def getTime: LocalTime = {
    this.dateTime.toLocalTime
  }
}

//object User {
//  def apply(name: String, email: String, password: String, caloriesPerDay: Int, registered: Date, roles: List[Role]): User =
//    User(new ObjectId(), name, email, password, caloriesPerDay, registered, roles)
//}

case class User(@BsonId var id: String, name:String, email:String, password:String, caloriesPerDay:Int, registered:Date, roles: Set[Role]) {

  override def toString: String = s"User {id= ${id}, name= ${name}, email= ${email}, caloriesPerDay= ${caloriesPerDay}}"

  def idToInt = Map ("id" -> id.toInt)

  def incrementId(): Int = id.toInt + 1
}

object Role extends Enumeration {
  type Role = Value
  val User, Admin = Value
}