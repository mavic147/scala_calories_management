package com.app.web.routes

import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import com.app.AuthUtil
import com.app.dao.{MealDaoImpl, UserDaoImpl}
import com.app.model.User
import com.app.util.MealsUtil
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import org.json4s.{Formats, ShortTypeHints}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object MealRoute {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()

  val userDaoImpl: UserDaoImpl = UserDaoImpl()

  val authUtil: AuthUtil = AuthUtil()

  val mealsUtil = new MealsUtil()

//  val route: Route = concat (
//    get {
//      path("topjava/users") {
//        implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[User])))
//        val usersList = userDaoImpl.getAll
//        onComplete(usersList) {
//          case Success(value) => complete(HttpEntity(ContentTypes.`application/json`, write(value.map(user => user.toMap))))
//          case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
//        }
//      },
//      path("topjava/meals") {
//        implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[MealTo])))
//        val mealsList = mealDaoImpl.getAll(authUtil.authUserId)
//        onComplete(mealsList) {
//          case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
//            write(mealsUtil.getTos(value.toList, authUtil.authUserCaloriesPerDay).map(mealTo => mealTo.toMap))))
//          case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
//        }
//      }
//    }
//  )

  val requestHandler: HttpRequest => HttpResponse = {
    case HttpRequest(GET, Uri.Path("/topjava/users"), _, _, _) => {
      implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[User])))
      val usersList = userDaoImpl.getAll.map { user => user.toList.map { each => each.toMap } }
      println(write(usersList))
      onComplete(usersList) {
        case Success(value) => complete(HttpEntity(ContentTypes.`application/json`, write(value)))
        case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
      }
      HttpResponse(entity = HttpEntity(ContentTypes.`application/json`, write(usersList)))
    }
  }
}

object UserRoot {}