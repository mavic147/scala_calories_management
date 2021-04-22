package com.app.util

import com.app.model.{Meal, User}
import com.app.util.exception.EntityNotFoundException
import org.mongodb.scala.bson.ObjectId

case class ValidationUtil() {

  def checkNotFoundById[T](obj: T, id: ObjectId): T = {
    if (obj == null) checkNotFound("Not found entity with id = " + id)
    else obj
  }

  def checkNotFound[T](obj: T, message: String): T = {
    if (obj == null) checkNotFound("Not found entity with " + message)
    else obj
  }

  def checkNotFound[T](message: String): Nothing = {
    throw new EntityNotFoundException(message)
  }

  def getMealInstance[T](obj: T): Meal = {
    obj.asInstanceOf[Meal]
  }

  def getUserInstance[T](obj: T): User = {
    obj.asInstanceOf[User]
  }

  def isNew[T](obj: T): Boolean = {
    if (obj.getClass.isInstance(Meal)) {
      val meal: Meal = getMealInstance(obj)
      meal._id == null
    } else {
      val user: User = getUserInstance(obj)
      user._id == null
    }
  }

  def checkNew[T](obj: T): Unit = {
    if (obj.getClass.isInstance(Meal)) {
      val meal: Meal = getMealInstance(obj)
      if (!isNew(meal)) throw new IllegalArgumentException(meal + " must be new (id = null)!")
    } else {
      val user: User = getUserInstance(obj)
      if (!isNew(user)) throw new IllegalArgumentException(user + " must be new (id = null)!")
    }
  }

  def checkIdConsistency[T](obj: T, id: String): Unit = {
    if (obj.getClass.isInstance(Meal)) {
      var meal: Meal = getMealInstance(obj)
      if (isNew(meal)) meal._id = id
      else if (meal._id != id) throw new IllegalArgumentException(meal + " must be with id=" + id)
    } else {
      var user: User = getUserInstance(obj)
      if (isNew(user)) user._id = id
      else if (user._id != id) throw new IllegalArgumentException(user + " must be with id=" + id)
    }
  }

}

