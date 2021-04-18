db.users.drop()
db.meals.drop()
db.users.dropIndex("email_1")
db.meals.dropIndex("userId_1_dateTime_1")
db.users.createIndex({"email": 1}, {unique: true})
db.meals.createIndex({"userId": 1, "dateTime": 1}, {unique: true})

// use calories_management
db.createCollection("users")
let user_id = new ObjectId()
let admin_id = new ObjectId()
db.users.insertMany([{_id: user_id, name: "UserAlice", email: "user@yandex.ru", password: "user", roles: ["User"]},
    {_id: admin_id, name: "AdminMark", email: "admin@gmail.com", password: "admin", roles: ["Admin", "User"]}])

let date1 = new Date(2021, 1, 30, 10, 0);
let date2 = new Date(2021, 1, 30, 13, 0);
let date3 = new Date(2021, 1, 30, 20, 0);
let date4 = new Date(2021, 1, 31, 0, 0);
let date5 = new Date(2021, 1, 31, 10, 0);
let date6 = new Date(2021, 1, 31, 14, 0);
let date7 = new Date(2021, 1, 31, 19, 0);
let date8 = new Date(2021, 1, 31, 14, 0);
let date9 = new Date(2021, 1, 31, 21, 0);
db.createCollection("meals")
db.meals.insertMany([
    {
        dateTime: date1,
        description: "Омлет с грибами",
        calories: 500,
        userId: user_id
    },
    {dateTime: date2, description: "Гаспачо", calories: 500, userId: user_id},
    {
        dateTime: date3,
        description: "Паста с морепродуктами",
        calories: 1000,
        userId: user_id
    },
    {
        dateTime: date4,
        description: "Еда на граничное значение",
        calories: 100,
        userId: user_id
    },
    {
        dateTime: date5,
        description: "Яичница и творог",
        calories: 400,
        userId: user_id
    },
    {dateTime: date6, description: "Пицца", calories: 1000, userId: user_id},
    {
        dateTime: date7,
        description: "Камчатский краб",
        calories: 510,
        userId: user_id
    },
    {
        dateTime: date8,
        description: "Блины для админа",
        calories: 510,
        userId: admin_id
    },
    {
        dateTime: date9,
        description: "Гамбургер с колой для админа",
        calories: 1500,
        userId: admin_id
    }
])