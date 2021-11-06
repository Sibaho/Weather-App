package com.pancesatria.weatherapp.data.source.remote

import javax.inject.Inject

/**
 * Created by Pance Satria on 11/4/2021.
 */
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getNext3DaysForecastByCity(city: String) = apiService.getNext3DaysForecastByCity(city)
}