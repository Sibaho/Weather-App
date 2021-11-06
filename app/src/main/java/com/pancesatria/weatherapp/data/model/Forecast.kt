package com.pancesatria.weatherapp.data.model

data class Forecast(
    val dt: Int,
    val humidity: Int,
    val speed: Double,
    val temp: Temp,
    val weather: List<Weather>
)