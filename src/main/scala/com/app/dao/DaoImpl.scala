package com.app.dao

import com.app.dao.DaoImpl.mongoClient
import com.app.model.{Meal, User}
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters.{and, equal}
import org.mongodb.scala.result.InsertOneResult
import org.mongodb.scala.{FindObservable, MongoClient, MongoCollection, MongoDatabase, result}

import java.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object DaoImpl {
  val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
}

case class MealDaoImpl() extends MealDao {

  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[Meal]), DEFAULT_CODEC_REGISTRY)
  val db: MongoDatabase = mongoClient.getDatabase("calories_management").withCodecRegistry(codecRegistry)
  val mealCollection: MongoCollection[Meal] = db.getCollection("meals")

  override def getOne(id: ObjectId, userId: ObjectId): Unit = {
    val getOneOp = mealCollection.find(and(equal("_id", id), equal("userId", userId))).toFuture()
    getOneOp.onComplete {
      case Success(result: FindObservable[Meal]) => println(result)
      case Failure(ex: Exception) => println(s"Operation failed with $ex")
    }
  }

  override def delete(id: ObjectId, userId: ObjectId): Future[result.DeleteResult]  = {
    mealCollection.deleteOne(and(equal("_id", id), equal("userId", userId))).toFuture()
  }

  override def getAll(userId: ObjectId): Unit = {
    val getAllOp = mealCollection.find(equal("userId", userId)).toFuture()
    getAllOp.onComplete {
      case Success(result: FindObservable[Meal]) => println(result)
      case Failure(ex: Exception) => println(s"Operation failed with $ex")
    }
  }

  override def getBetweenDates(startDateTime: LocalDateTime, endDateTime: LocalDateTime, userId: ObjectId): List[Meal] = ???

  override def create(meal: Meal): Unit = {
    val createOp = mealCollection.insertOne(meal).toFuture()
    createOp.onComplete {
      case Success(result: InsertOneResult) => result
      case Failure(ex: Exception) => println(s"Operation failed with $ex")
    }
  }

  override def update(meal: Meal, userId: ObjectId): Future[Meal] = {
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

  override def getOne(id: Int): Future[Seq[User]] = {
    userCollection.find(equal("_id", id)).toFuture()
  }

  override def delete(id: Int): Future[result.DeleteResult] = {
    userCollection.deleteOne(equal("_id", id)).toFuture()
  }

  override def update(user: User): Future[User] = {
    userCollection.findOneAndReplace(equal("_id", user._id), user).toFuture()
  }

  override def create(user: User): Future[result.InsertOneResult] = {
    userCollection.insertOne(user).toFuture()
  }
}