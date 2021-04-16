package com.app.dao

import com.app.dao.DaoImpl.mongoClient
import com.app.model.{Meal, User}
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

import java.time.LocalDateTime

object DaoImpl {
  val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
}

case class MealDaoImpl() extends MealDao {
  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[Meal]), DEFAULT_CODEC_REGISTRY)
  val db: MongoDatabase = mongoClient.getDatabase("calories_management").withCodecRegistry(codecRegistry)
  val mealCollection: MongoCollection[Meal] = db.getCollection("meals")

  override def getOne(id: Int, userId: Int): Meal = {
//    mealCollection.find(equal("_id", id))
    ???
  }

  override def delete(id: Int, userId: Int): Unit = ???

  override def getAll(userId: Int): List[Meal] = {
//    mealCollection.find()
    ???
  }

  override def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: Int): List[Meal] = ???

  override def create(meal: Meal, userId: ObjectId): Meal = {
    mealCollection.insertOne(meal)
    meal
  }

  override def update(meal: Meal, userId: Int): Unit = ???
}

case class UserDaoImpl() extends UserDao {

  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[User]), DEFAULT_CODEC_REGISTRY)
  val db: MongoDatabase = mongoClient.getDatabase("calories_management").withCodecRegistry(codecRegistry)
  val userCollection: MongoCollection[User] = db.getCollection("users")

  override def getAll: List[User] = ???

  override def getByEmail(email: String): User = ???

  override def getOne(id: Int): User = ???

  override def delete(id: Int): Unit = ???

  override def update(user: User): Unit = ???

  override def create(user: User): User = ???
}