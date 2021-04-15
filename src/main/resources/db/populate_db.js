db.users.remove({})
db.user_roles.remove({})
db.meals.remove({})

//Обязательно раскоментить при накате!!!
// use calories_management
db.createCollection("users")
userId = ObjectId()
adminId = ObjectId()
db.users.insertMany([{_id:userId, name: "UserAlice", email: "user@yandex.ru", password: "user"},
    {_id:adminId, name: "AdminMark", email: "admin@gmail.com", password: "admin"}])

db.createCollection("user_roles")
db.user_roles.insertMany([{role: "User", user_id:userId},
    {role: "Admin", user_id:adminId},
    {role: "User", user_id:adminId}])

db.createCollection("meals")
db.meals.insertMany([{
        description: "Омлет с грибами",
        dateTime: "2021-01-30 10:00:00",
        calories: 500,
        user_id:userId
    },
    {description: "Гаспачо", dateTime: "2021-01-30 13:00:00", calories: 500, user_id:userId},
    {
        description: "Паста с морепродуктами",
        dateTime: "2021-01-30 20:00:00",
        calories: 1000,
        user_id:userId
    },
    {
        description: "Еда на граничное значение",
        dateTime: "2021-01-31 0:00:00",
        calories: 100,
        user_id:userId
    },
    {
        description: "Яичница и творог",
        dateTime: "2021-01-31 10:00:00",
        calories: 400,
        user_id:userId
    },
    {description: "Пицца", dateTime: "2021-01-31 13:00:00", calories: 1000, user_id:userId},
    {
        description: "Камчатский краб",
        dateTime: "2021-01-31 20:00:00",
        calories: 510,
        user_id:userId
    },
    {
        description: "Блины для админа",
        dateTime: "2021-01-31 14:00:00",
        calories: 510,
        user_id:adminId
    },
    {
        description: "Гамбургер с колой для админа",
        dateTime: "2021-01-31 21:00:00",
        calories: 1500,
        user_id:adminId
    }])