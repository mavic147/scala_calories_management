// use calories_management
db.users.drop()
db.meals.drop()
// db.users.dropIndex("email_1")
// db.meals.dropIndex("userId_1_dateTime_1")
db.users.createIndex({"email": 1}, {unique: true})
db.meals.createIndex({"userId": 1, "dateTime": 1}, {unique: true})

db.createCollection("users")
let user_id = "100000"
let admin_id = "100001"
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

let meal1_id = "100002"
let meal2_id = "100003"
let meal3_id = "100004"
let meal4_id = "100005"
let meal5_id = "100006"
let meal6_id = "100007"
let meal7_id = "100008"
let meal8_id = "100009"
let meal9_id = "100010"
db.createCollection("meals")
db.meals.insertMany([
    {
        _id: meal1_id,
        dateTime: date1,
        description: "Омлет с грибами",
        calories: 500,
        userId: user_id
    },
    {_id: meal2_id, dateTime: date2, description: "Гаспачо", calories: 500, userId: user_id},
    {
        _id: meal3_id,
        dateTime: date3,
        description: "Паста с морепродуктами",
        calories: 1000,
        userId: user_id
    },
    {
        _id: meal4_id,
        dateTime: date4,
        description: "Еда на граничное значение",
        calories: 100,
        userId: user_id
    },
    {
        _id: meal5_id,
        dateTime: date5,
        description: "Яичница и творог",
        calories: 400,
        userId: user_id
    },
    {_id: meal6_id, dateTime: date6, description: "Пицца", calories: 1000, userId: user_id},
    {
        _id: meal7_id,
        dateTime: date7,
        description: "Камчатский краб",
        calories: 510,
        userId: user_id
    },
    {
        _id: meal8_id,
        dateTime: date8,
        description: "Блины для админа",
        calories: 510,
        userId: admin_id
    },
    {
        _id: meal9_id,
        dateTime: date9,
        description: "Гамбургер с колой для админа",
        calories: 1500,
        userId: admin_id
    }
])