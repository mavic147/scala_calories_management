package com.app

import org.mongodb.scala.bson.ObjectId

case class AuthUtil() {
  private val DEFAULT_CALORIES_PER_DAY: Int = 2000
  //change whenever db script is executed
  private val FAKE_AUTH_ID: ObjectId = new ObjectId("607c966a0dd06493cc480fd1")

  private var id = FAKE_AUTH_ID

  val authUserId: ObjectId = id

  def setAuthUserId(id: ObjectId): Unit = {
    this.id = id
  }

  def authUserCaloriesPerDay: Int = DEFAULT_CALORIES_PER_DAY
}
