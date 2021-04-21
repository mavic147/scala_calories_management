package com.app.to

import java.time.LocalDateTime

case class MealTo(id: String, dateTime: LocalDateTime, description: String, calories: Int, excess: Boolean) {

  override def toString: String = s"MealTo {id= ${id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}, excess= ${excess}}"

  def idToInt = Map ("id" -> id.toInt)
}
