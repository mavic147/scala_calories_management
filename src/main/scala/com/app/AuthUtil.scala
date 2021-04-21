package com.app

case class AuthUtil() {
  private val DEFAULT_CALORIES_PER_DAY: Int = 2000
  //change whenever db script is executed
  private val FAKE_AUTH_ID: String = "6080108a7b5228092ac51dd6"

  private var id = FAKE_AUTH_ID

  val authUserId: String = id

  def setAuthUserId(id: String): Unit = {
    this.id = id
  }

  def authUserCaloriesPerDay: Int = DEFAULT_CALORIES_PER_DAY
}
