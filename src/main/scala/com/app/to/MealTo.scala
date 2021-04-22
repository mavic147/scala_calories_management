package com.app.to

import java.time.{LocalDateTime, ZoneOffset}

case class MealTo(id: String, dateTime: LocalDateTime, description: String, calories: Int, excess: Boolean) {

  override def toString: String = s"MealTo {id= ${id}, dateTime= ${dateTime}, description= ${description}, " +
    s"calories= ${calories}, excess= ${excess}}"

  def toMap = Map (
    "id" -> id.toInt,
    "dateTime" -> dateTime.toEpochSecond(ZoneOffset.UTC),
    "description" -> description,
    "calories" -> calories,
    "excess" -> excess
  )
}
