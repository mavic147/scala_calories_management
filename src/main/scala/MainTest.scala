import com.app.dao.MealDaoImpl
import org.mongodb.scala.bson.ObjectId

object MainTest extends App {

  val mealDaoImpl: MealDaoImpl = MealDaoImpl()
//  val createdUser: User = User("Andrew", "drew@gmail.com", "12345", 1900, new Date(), List(Role.User))
//  val createdMeal: Meal = Meal(LocalDateTime.of(2021, Month.MARCH, 2, 15, 0,
//    0), "Salad and coffee", 890, createdUser._id)
//  mealDaoImpl.create(createdMeal)
  println(mealDaoImpl.getAll(new ObjectId("607c966a0dd06493cc480fd1")))
//  mealDaoImpl.getOne(new ObjectId("607c47a6f0d4f87a930f7779"), new ObjectId("607c47a40ff3764c34a30196"))
  Thread.sleep(1000)
}
