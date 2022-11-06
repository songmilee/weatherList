package com.song.weatherlist.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.song.weatherlist.R
import com.song.weatherlist.data.City
import com.song.weatherlist.data.model.WeatherInfo
import com.song.weatherlist.data.model.WeatherItem
import com.song.weatherlist.data.model.WeatherViewType
import com.song.weatherlist.databinding.ItemWeatherContentsBinding
import com.song.weatherlist.databinding.ItemWeatherHeaderBinding

class MainAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataList = ArrayList<WeatherItem>()
    class HeaderVH(private val binding: ItemWeatherHeaderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: City) {
            binding.headerName = data.name
        }
    }

    class ContentsVH(private val binding: ItemWeatherContentsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherInfo) {
            binding.info = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == WeatherViewType.HEADER.type) {
            val binding = DataBindingUtil.inflate<ItemWeatherHeaderBinding>(LayoutInflater.from(parent.context), R.layout.item_weather_header, parent, false)
            return HeaderVH(binding)
        } else {
            val binding = DataBindingUtil.inflate<ItemWeatherContentsBinding>(LayoutInflater.from(parent.context), R.layout.item_weather_contents, parent, false)
            return ContentsVH(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HeaderVH) {
            val data = dataList[position].contents as City
            holder.bind(data)
        } else if(holder is ContentsVH) {
            val data = dataList[position].contents as WeatherInfo
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int = dataList[position].viewType.type

    fun updateData(data: List<WeatherItem>) {
        dataList.clear()
        dataList.addAll(data)

        notifyDataSetChanged()
    }
}