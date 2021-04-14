package com.app.model

import java.time.LocalDateTime
import java.util.Date

class Models {

  case class Meal(id:Int, dateTime: LocalDateTime, description:String, calories:Int) {

    override def toString: String = "Meal{" + "id=" + id + ", dateTime=" + dateTime + ", description='" + description + '\'' + ", calories=" + calories + '}'
  }

  case class User(id:Int, name:String, email:String, password:String, caloriesPerDay:Int, registered:Date) {

    override def toString: String = "User{" + "id=" + id + ", email=" + email + ", name=" + name + ", caloriesPerDay=" + caloriesPerDay + '}';
  }

}
