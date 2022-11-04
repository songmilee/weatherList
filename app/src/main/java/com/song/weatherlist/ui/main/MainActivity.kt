package com.song.weatherlist.ui.main

import androidx.activity.viewModels
import com.song.weatherlist.R
import com.song.weatherlist.databinding.ActivityMainBinding
import com.song.weatherlist.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    private val adapter = MainAdapter()
    override fun setup() {
        binding?.vm = mainViewModel
        binding?.weatherList?.adapter = adapter
    }
}