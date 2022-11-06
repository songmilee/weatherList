package com.song.weatherlist.data.model

data class WeatherItem(val contents: Any,val viewType: WeatherViewType)

enum class WeatherViewType(val type: Int) {
    HEADER(0),
    CONTENTS(1)
}