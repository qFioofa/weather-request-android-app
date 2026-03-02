package com.example.weather_request_android_app.utils

data class City(val name: String, val localized: String)

object Constants {
    val POPULAR_CITIES = listOf(
        City("Moscow", "Москва"),
        City("Saint Petersburg", "Санкт-Петербург"),
        City("Novosibirsk", "Новосибирск"),
        City("Yekaterinburg", "Екатеринбург"),
        City("Kazan", "Казань"),
        City("Nizhny Novgorod", "Нижний Новгород"),
        City("Chelyabinsk", "Челябинск"),
        City("Samara", "Самара"),
        City("Omsk", "Омск"),
        City("Rostov-on-Don", "Ростов-на-Дону"),
        City("Ufa", "Уфа"),
        City("Krasnoyarsk", "Красноярск"),
        City("Voronezh", "Воронеж"),
        City("Perm", "Пермь"),
        City("Volgograd", "Волгоград"),

        City("London", "Лондон"),
        City("Paris", "Париж"),
        City("Berlin", "Берлин"),
        City("Madrid", "Мадрид"),
        City("Rome", "Рим"),

        City("New York", "Нью-Йорк"),
        City("Los Angeles", "Лос-Анджелес"),
        City("Chicago", "Чикаго"),
        City("Houston", "Хьюстон"),
        City("Phoenix", "Феникс"),

        City("Tokyo", "Токио"),
        City("Seoul", "Сеул"),
        City("Beijing", "Пекин"),
        City("Shanghai", "Шанхай"),
        City("Singapore", "Сингапур")
    )
}