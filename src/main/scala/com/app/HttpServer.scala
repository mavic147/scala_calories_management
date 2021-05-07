package com.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.app.web.routes.MealRoute.route
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn

object HttpServer {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem = ActorSystem("simple-http")

    val log: Logger = LoggerFactory.getLogger(this.getClass)

    val port = 8081

    val bindingFuture = Http().newServerAt("localhost", port).bind(route)

    log.info(s"Server started at the port $port")

    println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
