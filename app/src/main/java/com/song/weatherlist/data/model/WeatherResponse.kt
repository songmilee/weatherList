package com.song.weatherlist.data.model

import com.google.gson.annotations.SerializedName

data class FiveDayWeatherResponse(
    @SerializedName("cod") val cod: Int,
    @SerializedName("message") val message: String,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<WeatherInfo>
)

data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)

data class Main(
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

data class WeatherInfo(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
)