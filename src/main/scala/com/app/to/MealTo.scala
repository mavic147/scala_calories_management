package com.app.to

import java.time.LocalDateTime
import org.mongodb.scala.bson.ObjectId

case class MealTo(id: ObjectId, dateTime: LocalDateTime, description: String, calories: Int, excess: Boolean) {

  override def toString: String = s"MealTo {id= ${id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}, excess= ${excess}"
}
