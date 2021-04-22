package com.app.model

import org.bson.codecs.pojo.annotations.BsonProperty

import java.time.{LocalDate, LocalDateTime, LocalTime, ZoneOffset}
import java.util.Date

//object Meal {
//  def apply(dateTime: LocalDateTime, description: String, calories: Int, userId: ObjectId): Meal =
//    Meal(new ObjectId(), dateTime, description, calories, userId)
//
//}

case class Meal(@BsonProperty("_id") var _id: String, dateTime: LocalDateTime, description: String, calories: Int,
                userId: String) {

  override def toString: String = s"Meal {id= ${_id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}}"

  def toMap = Map (
    "id" -> _id.toInt,
    "dateTime" -> dateTime.toEpochSecond(ZoneOffset.UTC),
    "description" -> description,
    "calories" -> calories,
    "userId" -> userId.toInt
  )

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

case class User(@BsonProperty("_id") var _id: String, name:String, email:String, password:String, caloriesPerDay: Int,
                registered:Date, roles: Set[String]) {

  override def toString: String = s"User {id= ${_id}, name= ${name}, email= ${email}, caloriesPerDay= ${caloriesPerDay}, " +
    s"roles= ${roles}"

  def toMap = Map (
    "id" -> _id.toInt,
    "name" -> name,
    "email" -> email,
    "password" -> password,
    "caloriesPerDay" -> caloriesPerDay,
    "registered" -> registered.getTime,
    "roles" -> roles
  )

  var idCounter = 150000

  def incrementId(): String = {
    idCounter = idCounter + 1
    idCounter.toString
  }
}

//object Role extends Enumeration {
//  type Role = Value
//  val User, Admin = Value
//}