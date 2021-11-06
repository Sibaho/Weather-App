package com.pancesatria.weatherapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pancesatria.weatherapp.data.model.City
import io.reactivex.Observable

/**
 * Created by Pance Satria on 11/4/2021.
 */
@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City): Long

    @Query("SELECT * FROM city")
    fun selectAllCity(): Observable<List<City>>
}