package com.app.web.routes


import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object MealRoute {
  val mealRoute: Route = {
    path("meal") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to meal</h1>"))
      }
    }
  }
}

object UserRoot {}