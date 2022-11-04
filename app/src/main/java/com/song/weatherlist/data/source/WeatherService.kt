package com.song.weatherlist.data.source

import com.song.weatherlist.BuildConfig
import com.song.weatherlist.data.model.FiveDayWeatherResponse
import com.song.weatherlist.data.model.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast")
    suspend fun getFiveDaysForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = BuildConfig.APP_ID
    ): FiveDayWeatherResponse
}