db.users.remove({})
db.user_roles.remove({})
db.meals.remove({})

// use calories_management
db.createCollection("users")
user = ({name: "UserAlice", email: "user@yandex.ru", password: "user"})
admin = ({name: "AdminMark", email: "admin@gmail.com", password: "admin"})
db.users.insertMany([user, admin])

db.createCollection("user_roles")
db.user_roles.insertMany([{role: "User", user_id: new DBRef('users', user._id)},
    {role: "Admin", user_id: new DBRef('users', admin._id)},
    {role: "User", user_id: new DBRef('users', admin._id)}])

db.createCollection("meals")
db.meals.insertMany([{
        description: "Омлет с грибами",
        dateTime: "2021-01-30 10:00:00",
        calories: 500,
        user_id: new DBRef('users', user._id)
    },
    {description: "Гаспачо", dateTime: "2021-01-30 13:00:00", calories: 500, user_id: new DBRef('users', user._id)},
    {
        description: "Паста с морепродуктами",
        dateTime: "2021-01-30 20:00:00",
        calories: 1000,
        user_id: new DBRef('users', user._id)
    },
    {
        description: "Еда на граничное значение",
        dateTime: "2021-01-31 0:00:00",
        calories: 100,
        user_id: new DBRef('users', user._id)
    },
    {
        description: "Яичница и творог",
        dateTime: "2021-01-31 10:00:00",
        calories: 400,
        user_id: new DBRef('users', user._id)
    },
    {description: "Пицца", dateTime: "2021-01-31 13:00:00", calories: 1000, user_id: new DBRef('users', user._id)},
    {
        description: "Камчатский краб",
        dateTime: "2021-01-31 20:00:00",
        calories: 510,
        user_id: new DBRef('users', user._id)
    },
    {
        description: "Блины для админа",
        dateTime: "2021-01-31 14:00:00",
        calories: 510,
        user_id: new DBRef('users', admin._id)
    },
    {
        description: "Гамбургер с колой для админа",
        dateTime: "2021-01-31 21:00:00",
        calories: 1500,
        user_id: new DBRef('users', admin._id)
    }])