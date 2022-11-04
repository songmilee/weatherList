package com.song.weatherlist.usecase

import com.song.weatherlist.data.model.WeatherInfo
import com.song.weatherlist.data.repository.WeatherRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun getFiveDayWeatherList(lat: Double, lon: Double): List<WeatherInfo> {
        val kevlinToCelciusVal = 273.15
        val totalValues = weatherRepository.getFiveDayWeatherInfo(lat, lon)
        val groupResult = totalValues.asSequence().groupBy { it -> it.dtTxt.split(" ")[0] }

        val result = arrayListOf<WeatherInfo>()
        for ((k, v) in groupResult) {
            var maxTemp = Double.MIN_VALUE
            var minTemp = Double.MAX_VALUE
            for(wv in v) {
                maxTemp = Math.max(maxTemp, wv.main.tempMax)
                minTemp = Math.min(minTemp, wv.main.tempMin)
            }

            result.add(v[0].copy(dtTxt = k, main = v[0].main.copy(tempMax = maxTemp - kevlinToCelciusVal, tempMin = minTemp - kevlinToCelciusVal)))
        }
        android.util.Log.e("result", result.toString())
        return result
    }
}