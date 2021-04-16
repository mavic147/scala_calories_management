import com.app.dao.MealDaoImpl
import com.app.model.{Meal, Role, User}

import java.time.{LocalDateTime, Month}
import java.util.Date

object MainTest extends App {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()
  val createdUser: User = User("Andrew", "drew@gmail.com", "12345", 1900, new Date(), List(Role.User))
  val createdMeal: Meal = Meal(LocalDateTime.of(2021, Month.JANUARY, 30, 14, 0,
    0), "Lunch", 798, createdUser._id)
  val meal: Meal = mealDaoImpl.create(createdMeal)
  if (createdMeal.eq(meal)) println(meal)

}
