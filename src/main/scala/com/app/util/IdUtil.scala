package com.app.util

case class IdUtil() {
  var idCounter = 100011

  def incrementId(): String = {
    idCounter = idCounter + 1
    idCounter.toString
  }
}
