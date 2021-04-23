package com.app.dao

import com.app.dao.DaoImpl.mongoClient
import com.app.model.{Meal, User}
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters.{and, equal, gte, lt}
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase, result}

import java.time.LocalDateTime
import scala.concurrent.Future

object DaoImpl {
  val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
}

case class MealDaoImpl() extends MealDao {

  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[Meal]), DEFAULT_CODEC_REGISTRY)
  val db: MongoDatabase = mongoClient.getDatabase("calories_management").withCodecRegistry(codecRegistry)
  val mealCollection: MongoCollection[Meal] = db.getCollection("meals")

  override def getOne(id: String, userId: String): Future[Seq[Meal]] = {
    mealCollection.find(and(equal("_id", id), equal("userId", userId))).toFuture()
  }

  override def delete(id: String, userId: String): Future[result.DeleteResult]  = {
    mealCollection.deleteOne(and(equal("_id", id), equal("userId", userId))).toFuture()
  }

  override def getAll(userId: String): Future[Seq[Meal]] = {
    mealCollection.find(equal("userId", userId)).toFuture()
  }

  override def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: String): Future[Seq[Meal]] = {
    mealCollection.find(and(gte("dateTime", startDateTime), lt("dateTime", endDateTime),
      equal("userId", userId))).toFuture()
  }

  override def create(meal: Meal): Future[result.InsertOneResult] = {
    mealCollection.insertOne(meal).toFuture()
  }

  override def update(meal: Meal, userId: String): Future[Meal] = {
    mealCollection.findOneAndReplace(and(equal("_id", meal._id), equal("userId", userId)), meal).toFuture()
  }
}

case class UserDaoImpl() extends UserDao {

  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[User]), DEFAULT_CODEC_REGISTRY)
  val db: MongoDatabase = mongoClient.getDatabase("calories_management").withCodecRegistry(codecRegistry)
  val userCollection: MongoCollection[User] = db.getCollection("users")

  override def getAll: Future[Seq[User]] = {
    userCollection.find().toFuture()
  }

  override def getByEmail(email: String): Future[Seq[User]] = {
    userCollection.find(equal("email", email)).toFuture()
  }

  override def getOne(id: String): Future[Seq[User]] = {
    userCollection.find(equal("_id", id)).toFuture()
  }

  override def delete(id: String): Future[result.DeleteResult] = {
    userCollection.deleteOne(equal("_id", id)).toFuture()
  }

  override def update(user: User): Future[User] = {
    userCollection.findOneAndReplace(equal("_id", user._id), user).toFuture()
  }

  override def create(user: User): Future[result.InsertOneResult] = {
    userCollection.insertOne(user).toFuture()
  }
}