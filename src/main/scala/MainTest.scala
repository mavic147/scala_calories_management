import com.app.model.{Meal, Role, User}

import java.time.{LocalDateTime, Month}
import java.util.Date

object MainTest extends App {

//  val mealDaoImpl: MealDaoImpl = MealDaoImpl()
  val createdUser: User = User("Andrew", "drew@gmail.com", "12345", 1900, new Date(), List(Role.User))
  val createdMeal1: Meal = Meal(LocalDateTime.of(2021, Month.MARCH, 2, 15, 0,
    0), "Salad and coffee", 890, createdUser._id)
  val createdMeal2: Meal = Meal(LocalDateTime.of(2021, Month.MARCH, 2, 16, 0,
    0), "Doughnut & coffee", 1050, createdUser._id)
  val createdMeal3: Meal = Meal(LocalDateTime.of(2021, Month.MARCH, 3, 19, 0,
    0), "Porridge", 300, createdUser._id)

  //тестирование dao-классов
//    mealDaoImpl.create(createdMeal)
//  println(mealDaoImpl.getAll(new ObjectId("607c966a0dd06493cc480fd1")))
//  mealDaoImpl.getOne(new ObjectId("607c47a6f0d4f87a930f7779"), new ObjectId("607c47a40ff3764c34a30196"))

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

  Thread.sleep(1000)
}
