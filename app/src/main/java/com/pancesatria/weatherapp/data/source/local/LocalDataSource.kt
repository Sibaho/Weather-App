package com.pancesatria.weatherapp.data.source.local

import com.pancesatria.weatherapp.data.model.City
import javax.inject.Inject

/**
 * Created by Pance Satria on 11/4/2021.
 */
class LocalDataSource @Inject constructor(private val weatherDao: WeatherDao) {
    fun insertCity(city: City) = weatherDao.insert(city)

    fun getAllCity() = weatherDao.selectAllCity()
}