package com.app.web.routes

import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.app.dao.{MealDaoImpl, UserDaoImpl}
import com.app.model.{Meal, User}
import com.app.to.MealTo
import com.app.util._
import org.json4s.jackson.{Serialization, parseJson}
import org.json4s.{DefaultFormats, Formats, ShortTypeHints}
import org.slf4j.{Logger, LoggerFactory}

import java.time.format.{DateTimeFormatter, DateTimeParseException}
import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalDateTime, LocalTime, ZoneOffset}
import scala.util.{Failure, Success}

object MealRoute {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()

  val userDaoImpl: UserDaoImpl = UserDaoImpl()

  val authUtil: AuthUtil = AuthUtil()

  val mealsUtil = new MealsUtil()

  val dateTimeUtil = new DateTimeUtil()

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  val route: Route = {
    pathPrefix("topjava") {
      concat(

        path("users") {
          get {
            implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[User])))
            val usersList = userDaoImpl.getAll
            log.info("get all users")
            onComplete(usersList) {
              case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                Serialization.write(value.map(user => user.toMap))))
              case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
            }
          }
        },

        path("users") {
          post {
            implicit val formats: Formats = DefaultFormats
            entity(as[String]) { json =>
              val userId = parseJson(json).extract[UserIdJson].userId
              log.info(s"set id for user ${userId}")
              authUtil.setAuthUserId(userId)
              complete(StatusCodes.OK)
            }
          }
        },

        pathPrefix("meals") {
          concat(

            path("") {
              get {
                implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[MealTo])))
                val mealsList = mealDaoImpl.getAll(authUtil.authUserId)
                log.info(s"get all meals for user ${authUtil.authUserId}")
                onComplete(mealsList) {
                  case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                    Serialization.write(mealsUtil.getTos(value.toList, authUtil.authUserCaloriesPerDay)
                      .map(mealTo => mealTo.toMap))))
                  case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                }
              }
            },

            path("") {
              post {
                var id:String = ""
                var dateTime: LocalDateTime = LocalDateTime.now()
                var description = ""
                var calories: Int = 0
                entity(as[String]) { json =>
                  implicit val formats: Formats = DefaultFormats
                  val mealFields = parseJson(json).extract[MealFieldsJson]
                  id = mealFields.id
                  dateTime = LocalDateTime.ofEpochSecond(mealFields.dateTime.toLong, 0, ZoneOffset.UTC)
                  description = mealFields.description
                  calories = mealFields.calories.toInt
                  if (id == "0") {
                    val meal: Meal = Meal(authUtil.incrementId(), dateTime, description, calories, authUtil.authUserId)
                    val createOp = mealDaoImpl.create(meal)
                    log.info(s"create ${meal} for ${authUtil.authUserId}")
                    onComplete(createOp) {
                      case Success(_) => complete(StatusCodes.OK)
                      case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                    }
                  }
                  else {
                    implicit val mealFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Meal])))
                    val meal: Meal = Meal(id, dateTime, description, calories, authUtil.authUserId)
                    val updateOp = mealDaoImpl.update(meal, authUtil.authUserId)
                    log.info(s"update ${meal} for ${authUtil.authUserId}")
                    onComplete(updateOp) {
                      case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                        Serialization.write(value.toMap)(mealFormat)))
                      case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                    }
                  }
                }
              }
            },

            path("delete") {
              get {
                parameters("id") {
                  id => val deletionOp = mealDaoImpl.delete(id, authUtil.authUserId)
                    log.info(s"delete meal with id ${id} for user ${authUtil.authUserId}")
                    onComplete(deletionOp) {
                      case Success(_) => complete(StatusCodes.OK)
                      case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                    }
                }
              }
            },

            path("update") {
              get {
                parameters("id") {
                  id =>
                    implicit val formats: Formats = DefaultFormats
                    implicit val mealFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Meal])))
                    val updateOp = mealDaoImpl.getOne(id, authUtil.authUserId)
                    onComplete(updateOp) {
                      case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                        Serialization.write(value.toList.head.toMap)(mealFormat)))
                      case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
                    }
                }
              }
            },

            path("create") {
              get {
                implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[Meal])))
                val meal: Meal = Meal("0", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                  "", 1000, authUtil.authUserId)
                complete(HttpEntity(ContentTypes.`application/json`, Serialization.write(meal.toMap)))
              }
            },

            path("filter") {
              get {
                implicit val formats: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[MealTo])))
                val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                parameters("startDate".optional, "endDate".optional, "startTime".optional, "endTime".optional) {
                  (sD, eD, sT, eT) =>
                    var startDate = LocalDate.now()
                    try {
                      startDate = LocalDate.parse(sD.get, dateFormatter)
                    } catch {
                      case _: DateTimeParseException => startDate = null
                    }

                    var endDate = LocalDate.now()
                    try {
                      endDate = LocalDate.parse(eD.get, dateFormatter)
                    } catch {
                      case _: DateTimeParseException => endDate = null
                    }

                    var startTime: LocalTime = LocalTime.now()
                    try {
                      startTime = LocalTime.parse(sT.get, timeFormatter)
                    } catch {
                      case _: DateTimeParseException => startTime = null
                    }

                    var endTime: LocalTime = LocalTime.now()
                    try {
                      endTime = LocalTime.parse(eT.get, timeFormatter)
                    } catch {
                      case _: DateTimeParseException => endTime = null
                    }

                    val mealsDateFiltered = mealDaoImpl.getBetweenDates(dateTimeUtil.atStartOfDayOrMin(startDate),
                      dateTimeUtil.atStartOfNextDayOrMax(endDate), authUtil.authUserId)
                    log.info(s"get between dates ${startDate} - ${endDate}, time ${startTime} - ${endTime} " +
                      s"for user ${authUtil.authUserId}")
                    onComplete(mealsDateFiltered) {
                      case Success(value) => complete(HttpEntity(ContentTypes.`application/json`,
                        Serialization.write(mealsUtil.getFilteredTos(value.toList, authUtil.authUserCaloriesPerDay,
                          startTime, endTime)
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