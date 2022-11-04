package com.song.weatherlist.data.repository

import com.song.weatherlist.data.model.WeatherInfo
import com.song.weatherlist.data.source.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {
    suspend fun getFiveDayWeatherInfo(lat: Double, lon: Double): List<WeatherInfo> = weatherService.getFiveDaysForecast(lat, lon).list
}