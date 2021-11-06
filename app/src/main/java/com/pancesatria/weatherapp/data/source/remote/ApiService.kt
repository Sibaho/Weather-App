package com.pancesatria.weatherapp.data.source.remote

import com.pancesatria.weatherapp.data.model.ForecastResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pance Satria on 11/4/2021.
 */
interface ApiService {
    @GET("forecast/daily?cnt=3")
    fun getNext3DaysForecastByCity(@Query("q") q: String): Observable<ForecastResponse>
}