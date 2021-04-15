package com.app.model

import com.app.model.Role.Role

import java.time.LocalDateTime
import java.util.Date

case class Meal(id: Int, dateTime: LocalDateTime, description: String, calories: Int) {

  override def toString: String = s"Meal {id= ${id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}"
}

case class User(id:Int, name:String, email:String, password:String, caloriesPerDay:Int, registered:Date, roles: List[Role]) {

  override def toString: String = s"User {id= ${id}, name= ${name}, email= ${email}, caloriesPerDay= ${caloriesPerDay}}"
}

object Role extends Enumeration {
  type Role = Value
  val User, Admin = Value
}