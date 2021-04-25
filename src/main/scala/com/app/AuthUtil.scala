package com.app

case class AuthUtil() {
  private val DEFAULT_CALORIES_PER_DAY: Int = 2000

  var authUserId: String = "100000"

  def setAuthUserId(id: String): Unit = {
    this.authUserId = id
  }

  def authUserCaloriesPerDay: Int = DEFAULT_CALORIES_PER_DAY

  var idCounter = 100011

  def incrementId(): String = {
    idCounter = idCounter + 1
    idCounter.toString
  }
}
