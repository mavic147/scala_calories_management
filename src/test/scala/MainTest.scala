import com.app.dao.MealDaoImpl
import com.app.model.{Meal, Role, User}

import java.time.{LocalDateTime, Month}
import java.util.Date

object MainTest extends App {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()
  val createdUser: User = User("607c966a0dd06493cc480fd1", "Andrew", "drew@gmail.com", "12345", 1900, new Date(), Set(Role.User))
  val createdMeal1: Meal = Meal("3", LocalDateTime.of(2021, Month.MARCH, 2, 15, 0,
    0), "Salad and coffee", 890, createdUser.id)
  val createdMeal2: Meal = Meal("607c47a6f0d4f87a930f7780", LocalDateTime.of(2021, Month.MARCH, 2, 16, 0,
    0), "Doughnut & coffee", 1050, createdUser.id)
  val createdMeal3: Meal = Meal("607c47a6f0d4f87a930f7781", LocalDateTime.of(2021, Month.MARCH, 3, 19, 0,
    0), "Porridge", 300, createdUser.id)
  val createdMeal4: Meal = Meal("607c47a6f0d4f87a930f7782", LocalDateTime.of(2021, Month.MARCH, 3, 13, 0,
    0), "Fish & Chips", 800, createdUser.id)

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
}
