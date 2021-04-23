package com.app.web.routes

import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.app.AuthUtil
import com.app.dao.{MealDaoImpl, UserDaoImpl}
import com.app.model.{Meal, User}
import com.app.to.MealTo
import com.app.util.{DateTimeUtil, IdUtil, MealsUtil}
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import org.json4s.{Formats, ShortTypeHints}
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import java.time.{LocalDateTime, LocalTime}
import java.time.temporal.ChronoUnit
import scala.util.{Failure, Success}

object MealRoute {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()

  val userDaoImpl: UserDaoImpl = UserDaoImpl()

  val authUtil: AuthUtil = AuthUtil()

  val mealsUtil = new MealsUtil()

  val idUtil: IdUtil = IdUtil();

  val dateTimeUtil = new DateTimeUtil()

  val route: Route = {
    pathPrefix("topjava") {
      concat(
        path("users") {
          get {
            implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[User])))
            val usersList = userDaoImpl.getAll
            onComplete(usersList) {
              case Success(value) => complete(HttpEntity(ContentTypes.`application/json`, write(value.map(user => user.toMap))))
              case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
            }
          }
        },
        path("users") {
          post {
            entity(as[String]) map { e =>
//              log.info("POST request: " + e)
              var res = Array[String]()
              res = e.split("=")
              authUtil.setAuthUserId(res(1))
            }
            complete(StatusCodes.OK)
          }
        },
        pathPrefix("meals") {
          concat(
            path("") {
              get {
                implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[MealTo])))
                val mealsList = mealDaoImpl.getAll(authUtil.authUserId)
                onComplete(mealsList) {
                  case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                    write(mealsUtil.getTos(value.toList, authUtil.authUserCaloriesPerDay).map(mealTo => mealTo.toMap))))
                  case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                }
              }
            },
            path("") {
              post {
                ???
              }
            },
            path("delete") {
              delete {
                var id: String = ""
                entity(as[String]) map { e =>
                  var res = Array[String]()
                  res = e.split("=")
                  id = res(1)
                }
                val deletionOp = mealDaoImpl.delete(id, authUtil.authUserId)
                onComplete(deletionOp) {
                  case Success(value) => complete(StatusCodes.OK)
                  case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                }
              }
            },
            path("update") {
              get {
                implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Meal])))
                var id: String = ""
                entity(as[String]) map { e =>
                  var res = Array[String]()
                  res = e.split("=")
                  id = res(1)
                }
                val updateOp = mealDaoImpl.getOne(id, authUtil.authUserId)
                onComplete(updateOp) {
                  case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                    write(value.map(meal => meal.toMap))))
                  case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                }
              }
            },
            path("create") {
              get {
                implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Meal])))
                val meal: Meal  = Meal(idUtil.incrementId(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                  "", 1000, authUtil.authUserId)
                complete(HttpEntity(ContentTypes.`application/json`, write(meal.toMap)))
              }
            },
            path("filter") {
              get {
                implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[MealTo])))
                val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd")
                val timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss")
                parameters("startDate", "endDate", "startTime", "endTime") {
                  (sD, eD, sT, eT) =>
                    val startDate: LocalDate = LocalDate.parse(sD, dateFormatter)
                    val endDate: LocalDate = LocalDate.parse(eD, dateFormatter)
                    val startTime: LocalTime = LocalTime.parse(sT, timeFormatter)
                    val endTime: LocalTime = LocalTime.parse(eT, timeFormatter)
                    val mealsDateFiltered = mealDaoImpl.getBetweenDates(dateTimeUtil.atStartOfDayOrMin(startDate),
                      dateTimeUtil.atStartOfNextDayOrMax(endDate), authUtil.authUserId)
                    onComplete(mealsDateFiltered) {
                      case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                        write(mealsUtil.getFilteredTos(value.toList, authUtil.authUserCaloriesPerDay, startTime, endTime)
                          .map(mealTo => mealTo.toMap))))
                      case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                    }
                }
              }
            }
          )
        }
      )
    }
  }
}

object UserRoot {}