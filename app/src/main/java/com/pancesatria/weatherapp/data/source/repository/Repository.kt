package com.pancesatria.weatherapp.data.source.repository

import com.pancesatria.weatherapp.data.source.local.LocalDataSource
import com.pancesatria.weatherapp.data.model.City
import com.pancesatria.weatherapp.data.source.remote.RemoteDataSource
import javax.inject.Inject

/**
 * Created by Pance Satria on 11/4/2021.
 */
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getNext3DaysForecastByCity(city: String) = remoteDataSource.getNext3DaysForecastByCity(city)

    fun getAllCity() = localDataSource.getAllCity()

    fun insertCity(city: City) = localDataSource.insertCity(city)
}