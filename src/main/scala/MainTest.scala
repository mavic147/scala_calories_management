import com.app.dao.MealDaoImpl
import com.app.model.{Meal, Role, User}

import java.time.{LocalDateTime, Month}
import java.util.Date

object MainTest extends App {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()
  val createdUser: User = User("Andrew", "drew@gmail.com", "12345", 1900, new Date(), List(Role.User))
  val createdMeal: Meal = Meal(LocalDateTime.of(2021, Month.JANUARY, 30, 14, 0,
    0), "Brunch", 596, createdUser._id)
  mealDaoImpl.create(createdMeal)
  Thread.sleep(1000)
}
