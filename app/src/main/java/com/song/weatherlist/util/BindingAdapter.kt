package com.song.weatherlist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.song.weatherlist.R
import com.song.weatherlist.data.model.WeatherItem
import com.song.weatherlist.ui.main.MainAdapter

@BindingAdapter("bind:updateData")
fun updateData(view: RecyclerView, data: List<WeatherItem>?) {
    data?.let {
        val adapter = view.adapter as MainAdapter
        adapter.updateData(it)
    }
}

@BindingAdapter("bind:loadWeatherImage")
fun loadWeatherImage(view: ImageView, imageKey: String) {
    val resId = when(imageKey) {
        "01d", "01n" -> R.drawable.ic_01
        "02d", "02n" -> R.drawable.ic_02
        "03d", "03n" -> R.drawable.ic_03
        "04d", "04n" -> R.drawable.ic_04
        "09d", "09n" -> R.drawable.ic_09
        "10d", "10n" -> R.drawable.ic_10
        "11d", "11n" -> R.drawable.ic_11
        "13d", "13n" -> R.drawable.ic_13
        else -> R.drawable.ic_50
    }

    view.setImageResource(resId)
}