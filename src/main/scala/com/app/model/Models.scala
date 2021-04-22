package com.app.model

import com.app.model.Role.Role
import org.bson.codecs.pojo.annotations.BsonProperty

import java.time.{LocalDate, LocalDateTime, LocalTime}
import java.util.Date

//object Meal {
//  def apply(dateTime: LocalDateTime, description: String, calories: Int, userId: ObjectId): Meal =
//    Meal(new ObjectId(), dateTime, description, calories, userId)
//
//}

case class Meal(@BsonProperty("_id") var _id: String, dateTime: LocalDateTime, description: String, calories: Int, userId: String) {

  override def toString: String = s"Meal {id= ${_id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}}"

  def idToInt = Map ("id" -> _id.toInt)

  def userIdToInt = Map ("userId" -> userId.toInt)

  var idCounter = 100011

  def incrementId(): String = {
    idCounter = idCounter + 1
    idCounter.toString
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

case class User(@BsonProperty("_id") var _id: String, name:String, email:String, password:String, caloriesPerDay:Int, registered:Date, roles: Set[Role]) {

  override def toString: String = s"User {id= ${_id}, name= ${name}, email= ${email}, caloriesPerDay= ${caloriesPerDay}}"

  def idToInt = Map ("id" -> _id.toInt)

  var idCounter = 150000

  def incrementId(): String = {
    idCounter = idCounter + 1
    idCounter.toString
  }
}

object Role extends Enumeration {
  type Role = Value
  val User, Admin = Value
}