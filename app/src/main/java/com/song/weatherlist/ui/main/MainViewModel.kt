package com.song.weatherlist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.song.weatherlist.data.City
import com.song.weatherlist.data.model.WeatherInfo
import com.song.weatherlist.data.model.WeatherItem
import com.song.weatherlist.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainUseCase: MainUseCase) : ViewModel() {
    private val _weatherItems = MutableLiveData<ArrayList<WeatherItem>>()
    val weatherItem: LiveData<ArrayList<WeatherItem>> = _weatherItems
    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val resultData = ArrayList<WeatherItem>()
            for (i in City.values()) {
                resultData.add(WeatherItem(i, 0))
                val result = mainUseCase.getFiveDayWeatherList(i.lat, i.lon)
                val mapWeather = result.asSequence().map { info -> WeatherItem(info, 1) }.toList()
                resultData.addAll(mapWeather)
            }

            _weatherItems.postValue(resultData)
        }
    }
}