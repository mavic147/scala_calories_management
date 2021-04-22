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
//
//      },
//      path("topjava/meals") {
//
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
//    case HttpRequest(POST, Uri.Path("/topjava/users"), _, _, _) => {
//
//    }
//    case HttpRequest(GET, Uri.Path("/topjava/meals"), _, _, _) => {
//      implicit val mealToFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[MealTo])))
//      val mealsList = mealDaoImpl.getAll(authUtil.authUserId).map {
//        meal => mealsUtil.getTos(meal.toList, authUtil.authUserCaloriesPerDay).map { mealTo => mealTo.idToInt }
//      }
//      onComplete(mealsList) {
//        case Success(value) => complete(HttpEntity(ContentTypes.`application/json`, write(value)))
//        case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
//      }
//    }
  }

//  val route: Route = concat(
//    {
//      path("topjava/users") {
//        //        post {
//        //          parameter("userId") { userId =>
//        //            authUtil.setAuthUserId(new ObjectId(userId)
//        //
//        //          }
//        //        }
//        //        ,
//        get {
//          implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[User])))
//          val usersList = userDaoImpl.getAll.map { user => user.toList.map { each => each.idToInt}}
//          complete(HttpEntity(ContentTypes.`application/json`, write(usersList)))
//        }
//      }
//    },
//    path("topjava/meals") {
//      get {
//        implicit val mealToFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[MealTo])))
//        val mealsList = mealDaoImpl.getAll(authUtil.authUserId).map {
//          meal => mealsUtil.getTos(meal.toList, authUtil.authUserCaloriesPerDay).map { mealTo => mealTo.idToInt}
//        }
//        onComplete(mealsList) {
//          case Success(value) => complete(HttpEntity(ContentTypes.`application/json`, write(value)))
//          case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
//        }
//      }
//    }
//  )
}

object UserRoot {}