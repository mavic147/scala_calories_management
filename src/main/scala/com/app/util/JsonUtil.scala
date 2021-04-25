package com.app.util

case class UserIdJson(userId: String)

case class MealFieldsJson(id: String, dateTime: String, description: String, calories: String)

case class CreateUserJson(name: String, email: String, password: String)