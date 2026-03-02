package com.example.weather_request_android_app.data.repository

object WeatherUrlGenerator {
    private const val BASE_URL = "https://yandex.ru/pogoda"

    fun generateUrl(city: String, days: Int): String {
        val encodedCity = city.trim().lowercase().replace(" ", "-")
        return when (days) {
            1 -> "$BASE_URL/$encodedCity"
            5 -> "$BASE_URL/$encodedCity?via=srp"
            10 -> "$BASE_URL/$encodedCity?via=srp&details=true"
            else -> "$BASE_URL/$encodedCity"
        }
    }
}