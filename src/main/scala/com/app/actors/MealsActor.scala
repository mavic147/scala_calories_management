package com.app.actors

import akka.actor.Actor
import com.app.actors.MealsActor.GetMealMessage
import com.app.dao.MealDaoImpl
import com.app.model.Meal
import com.app.util.MealsUtil
import org.mongodb.scala.bson.ObjectId

import java.io.Serializable
import scala.concurrent.ExecutionContext.Implicits.global

object MealsActor {
  case class ActionPerformed(description: String) extends Serializable
  case class GetMealMessage(mealId: ObjectId, userId: ObjectId) extends Serializable
  case class CreateMealMessage(meal: Meal)
}

case class MealsActor() extends Actor {
  val mealDao: MealDaoImpl = MealDaoImpl()
  val mealsUtil: MealsUtil = new MealsUtil()

  override def receive: Receive = {
    case GetMealMessage(mealId, userId) => {
      val result = mealDao.getOne(mealId, userId).map {
        meal => mealsUtil.getTos(meal.toList, 2000)//убрать приведение к mealTo
      }
      sender() ! (result, self)}
  }

}
