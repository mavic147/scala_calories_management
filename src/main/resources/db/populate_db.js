db.users.remove({})
db.meals.remove({})
db.users.dropIndex("email_1")
db.meals.dropIndex("userId_1_dateTime_1")
db.users.createIndex({"email":1}, {unique:true})
db.meals.createIndex({"userId":1, "dateTime":1}, {unique:true})

//Обязательно раскоментить при накате!!!
// use calories_management
db.createCollection("users")
user_id = ObjectId()
admin_id = ObjectId()
db.users.insertMany([{_id:user_id, name: "UserAlice", email: "user@yandex.ru", password: "user", roles:["User"]},
    {_id:admin_id, name: "AdminMark", email: "admin@gmail.com", password: "admin", roles:["Admin", "User"]}])

db.createCollection("meals")
db.meals.insertMany([{
        dateTime: "2021-01-30 10:00:00",
        description: "Омлет с грибами",
        calories: 500,
        userId:user_id
    },
    {dateTime: "2021-01-30 13:00:00", description: "Гаспачо",  calories: 500, userId:user_id},
    {
        dateTime: "2021-01-30 20:00:00",
        description: "Паста с морепродуктами",
        calories: 1000,
        userId:user_id
    },
    {
        dateTime: "2021-01-31 0:00:00",
        description: "Еда на граничное значение",
        calories: 100,
        userId:user_id
    },
    {
        dateTime: "2021-01-31 10:00:00",
        description: "Яичница и творог",
        calories: 400,
        userId:user_id
    },
    {dateTime: "2021-01-31 13:00:00", description: "Пицца", calories: 1000, userId:user_id},
    {
        dateTime: "2021-01-31 20:00:00",
        description: "Камчатский краб",
        calories: 510,
        userId:user_id
    },
    {
        dateTime: "2021-01-31 14:00:00",
        description: "Блины для админа",
        calories: 510,
        userId:admin_id
    },
    {
        dateTime: "2021-01-31 21:00:00",
        description: "Гамбургер с колой для админа",
        calories: 1500,
        userId:admin_id
    }])