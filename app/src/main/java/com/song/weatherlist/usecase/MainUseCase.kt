package com.song.weatherlist.usecase

import com.song.weatherlist.data.model.WeatherInfo
import com.song.weatherlist.data.repository.WeatherRepository
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MainUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun getFiveDayWeatherList(lat: Double, lon: Double): List<WeatherInfo> {
        val kelvinToCelsiusVal = 273.15
        val totalValues = weatherRepository.getFiveDayWeatherInfo(lat, lon)
        val groupResult = totalValues.groupBy { it.dtTxt.split(" ")[0] }

        val result = arrayListOf<WeatherInfo>()
        for ((k, v) in groupResult) {
            var maxTemp = Double.MIN_VALUE
            var minTemp = Double.MAX_VALUE
            for (wv in v) {
                maxTemp = Math.max(maxTemp, wv.main.tempMax)
                minTemp = Math.min(minTemp, wv.main.tempMin)
            }

            result.add(
                v[0].copy(
                    dtTxt = convertDateToDayString(k),
                    main = v[0].main.copy(
                        tempMax = maxTemp - kelvinToCelsiusVal,
                        tempMin = minTemp - kelvinToCelsiusVal
                    )
                )
            )
        }

        return result
    }

    private fun convertDateToDayString(dateString: String): String {
        val parseDate =
            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyy-MM-dd")).atStartOfDay()
        val currentDate = LocalDate.now().atStartOfDay()

        val diff = Math.abs(Duration.between(parseDate, currentDate).toDays())

        return if (diff == 0L) "Today"
        else if (diff == 1L) "Tomorrow"
        else dateString
    }
}