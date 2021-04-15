package com.app.model

import java.time.LocalDateTime
import java.util.Date

case class Meal(id: Int, dateTime: LocalDateTime, description: String, calories: Int) {

  override def toString: String = s"Meal {id= ${id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}"
}

case class User(id:Int, name:String, email:String, password:String, caloriesPerDay:Int, registered:Date) {

  override def toString: String = s"User {id= ${id}, name= ${name}, email= ${email}, caloriesPerDay= ${caloriesPerDay}}"
}