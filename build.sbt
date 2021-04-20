name := "calories_management"

version := "0.1"

scalaVersion := "2.13.5"

lazy val MongoDriverVersion = "4.2.3"
lazy val LogbackVersion = "1.2.3"
lazy val ScalaLoggingVersion = "3.9.3"
lazy val AkkaVersion = "2.6.8"
lazy val AkkaHttpVersion = "10.2.4"
lazy val Json4sVersion = "3.7.0-M15"

libraryDependencies ++= Seq("org.mongodb.scala" %% "mongo-scala-driver" % MongoDriverVersion,
                            "ch.qos.logback" % "logback-classic" % LogbackVersion,
                            "com.typesafe.scala-logging" %% "scala-logging" % ScalaLoggingVersion,
                            "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
                            "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
                            "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
                            "org.json4s" %% "json4s-jackson" % Json4sVersion)