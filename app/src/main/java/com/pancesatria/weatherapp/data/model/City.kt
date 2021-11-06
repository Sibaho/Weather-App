package com.pancesatria.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "city",
    indices = [Index(value = arrayOf("id"), unique = true)]
)
data class City(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="country") val country: String,
    @ColumnInfo(name="name") val name: String,
)