import com.app.dao.{MealDaoImpl, UserDaoImpl}
import com.app.model.User
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import org.json4s.{Formats, ShortTypeHints}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object MainTest extends App {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()
  val userDaoImpl: UserDaoImpl = UserDaoImpl()
//  val createdUser: User = User("607c966a0dd06493cc480fd1", "Andrew", "drew@gmail.com", "12345", 1900, new Date(), Set(Role.User))
//  val createdMeal1: Meal = Meal("3", LocalDateTime.of(2021, Month.MARCH, 2, 15, 0,
//    0), "Salad and coffee", 890, createdUser.id)
//  val createdMeal2: Meal = Meal("607c47a6f0d4f87a930f7780", LocalDateTime.of(2021, Month.MARCH, 2, 16, 0,
//    0), "Doughnut & coffee", 1050, createdUser.id)
//  val createdMeal3: Meal = Meal("607c47a6f0d4f87a930f7781", LocalDateTime.of(2021, Month.MARCH, 3, 19, 0,
//    0), "Porridge", 300, createdUser.id)
//  val createdMeal4: Meal = Meal("607c47a6f0d4f87a930f7782", LocalDateTime.of(2021, Month.MARCH, 3, 13, 0,
//    0), "Fish & Chips", 800, createdUser.id)

  //тестирование dao-классов
  //      mealDaoImpl.create(createdMeal1)
  //    println(mealDaoImpl.getAll(new ObjectId("607c966a0dd06493cc480fd1")))
  //    mealDaoImpl.getOne(new ObjectId("607c47a6f0d4f87a930f7779"), new ObjectId("607c47a40ff3764c34a30196"))
  //  println(mealDaoImpl.getBetweenDates(LocalDateTime.of(2021, Month.JANUARY, 30, 9, 0, 0),
  //    LocalDateTime.of(2021, Month.JANUARY, 30, 19, 0, 0),
  //    new ObjectId("607c966a0dd06493cc480fd1")))
  //  println(mealDaoImpl.delete(new ObjectId("607c966dea9864743f82c11f"),
  //    new ObjectId("607c966a0dd06493cc480fd1")))
  //    Thread.sleep(1000)

  //тестирование лямбд для создания и фильтрации MealTo
  //  val meals: List[Meal] = List(createdMeal1, createdMeal2, createdMeal3)
  //  val mealsByDate1: Map[LocalDate, List[Meal]] = meals.groupBy(_.getDate)
  //  val caloriesSumByDate: Map[LocalDate, Int] = meals.groupBy(_.getDate).map { x => (x._1, x._2.map(meal => meal.calories).sum) }
  //
  //  val caloriesList = meals.map {x => x.calories}
  //  val sum = caloriesList.sum
  //  val resultMap: Map[LocalDate,Int] = {
  //    mealsByDate1.map { x => (x._1, x._2.map(meal => meal.calories).sum) }
  //  }

  //тестирование dateTimeUtil & mealsUtil
  //  val mealsUtil = new MealsUtil
  //  val meals: List[Meal] = List(createdMeal1, createdMeal2, createdMeal3, createdMeal4)
  //  println(mealsUtil.getTos(meals, 1500))
  //  println(mealsUtil.getFilteredTos(meals, 1500, LocalTime.of(10, 0, 0),
  //    LocalTime.of(16, 0, 0)))

//  implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[User])))
//  val usersList = userDaoImpl.getAll.map { user => user.toList.map { each => each.idToInt } }
//  onComplete(usersList) {
//    case Success(value) => complete(HttpEntity(ContentTypes.`application/json`, write(value)))
//    case Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
//  }

//пример обработки meals!
//  val mealsUtil = new MealsUtil()
//  implicit val formats: AnyRef with Formats = {
//    Serialization.formats(FullTypeHints(List(classOf[Meal])))
//  }
//  val allMeals = Await.result(mealDaoImpl.getAll(authUtil.authUserId), Duration.Inf)
//  println(allMeals)
//  val allMealsTo: List[MealTo] = mealsUtil.getTos(allMeals.toList, authUtil.authUserCaloriesPerDay)
//  println(allMealsTo)
//  val result = write(allMealsTo.map(mealTo => mealTo.toMap))
//  val result = write(allMeals.map(meal => meal.toMap))
//  println(result)


  implicit val userFormat: AnyRef with Formats = Serialization.formats(ShortTypeHints(List(classOf[User])))
  val allUsers = Await.result(userDaoImpl.getAll, Duration.Inf)
  println(allUsers)
  println(write(allUsers.map(user => user.toMap)))

}
