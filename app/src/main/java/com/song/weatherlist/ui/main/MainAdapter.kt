package com.song.weatherlist.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.song.weatherlist.R
import com.song.weatherlist.data.City
import com.song.weatherlist.data.model.WeatherInfo
import com.song.weatherlist.data.model.WeatherItem
import com.song.weatherlist.data.model.WeatherViewType
import com.song.weatherlist.databinding.ItemWeatherContentsBinding
import com.song.weatherlist.databinding.ItemWeatherHeaderBinding

class MainAdapter : ListAdapter<WeatherItem, RecyclerView.ViewHolder>(diff) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<WeatherItem>() {
            override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
                return (oldItem.contents as WeatherInfo).dtTxt == (newItem.contents as WeatherInfo).dtTxt
            }

        }
    }

    class HeaderVH(private val binding: ItemWeatherHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: City) {
            binding.headerName = data.name
        }
    }

    class ContentsVH(private val binding: ItemWeatherContentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherInfo) {
            binding.info = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == WeatherViewType.HEADER.type) {
            val binding = DataBindingUtil.inflate<ItemWeatherHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_weather_header,
                parent,
                false
            )
            return HeaderVH(binding)
        } else {
            val binding = DataBindingUtil.inflate<ItemWeatherContentsBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_weather_contents,
                parent,
                false
            )
            return ContentsVH(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderVH) {
            val data = currentList[position].contents as City
            holder.bind(data)
        } else if (holder is ContentsVH) {
            val data = currentList[position].contents as WeatherInfo
            holder.bind(data)
        }
    }

    override fun getItemViewType(position: Int): Int = currentList[position].viewType.type

}