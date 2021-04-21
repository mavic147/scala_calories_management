package com.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.app.web.routes.MealRoute.requestHandler
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn

object HttpServer {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem = ActorSystem("simple-http")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val log: Logger = LoggerFactory.getLogger(this.getClass)

    val port = 8081

//    val bindingFuture = Http().newServerAt("localhost", port).bind(route)
    val bindingFuture = Http().newServerAt("localhost", port).bindSync(requestHandler)

    log.info(s"Server started at the port $port")

    println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
