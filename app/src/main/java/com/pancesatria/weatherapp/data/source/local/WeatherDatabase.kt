package com.pancesatria.weatherapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pancesatria.weatherapp.data.model.City

/**
 * Created by Pance Satria on 11/4/2021.
 */
@Database(
    entities = [City::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase(){
    abstract val weatherDao: WeatherDao
}