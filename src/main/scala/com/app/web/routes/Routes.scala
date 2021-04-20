package com.app.web.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.app.dao.MealDaoImpl
import com.app.model.Meal
import org.json4s.{DefaultFormats, FieldSerializer, Formats}
import org.mongodb.scala.bson.ObjectId

object MealRoute {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()

  val mealSerializer = FieldSerializer[Meal]()

  implicit val formats: Formats = DefaultFormats + mealSerializer

  val mealRoute: Route = {
    path("meal") {
      get {
        val meal = mealDaoImpl.getOne(new ObjectId("607c966dea9864743f82c11c"),
          new ObjectId("607c966a0dd06493cc480fd1"))
        onSuccess(meal) {
//          case result => complete(result.head)
          case _      => complete(StatusCodes.NotFound)
        }
      }
    }
  }
}

object UserRoot {}