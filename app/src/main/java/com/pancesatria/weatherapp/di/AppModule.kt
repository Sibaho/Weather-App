package com.pancesatria.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pancesatria.weatherapp.BuildConfig
import com.pancesatria.weatherapp.data.source.local.WeatherDatabase
import com.pancesatria.weatherapp.data.source.remote.ApiService
import com.pancesatria.weatherapp.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Pance Satria on 11/4/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalUrl = chain.request().url
                val url = originalUrl.newBuilder()
                    .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY)
                    .build()
                val requestBuilder = chain.request().newBuilder().url(url)
                return@addInterceptor chain.proceed(requestBuilder.build())
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
        return okHttpBuilder

    }

    @Singleton
    @Provides
    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideLocalDb(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            "weather_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideWeatherDao(db: WeatherDatabase) = db.weatherDao
}