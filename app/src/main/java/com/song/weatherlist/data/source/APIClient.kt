package com.song.weatherlist.data.source

import com.song.weatherlist.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = BuildConfig.BASE_URL
    fun getRetrofitClient(): WeatherService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherService::class.java)
    }
}