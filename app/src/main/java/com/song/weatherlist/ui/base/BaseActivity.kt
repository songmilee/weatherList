package com.song.weatherlist.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(private val resId: Int): AppCompatActivity() {
    protected var binding:T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, resId)
        binding?.lifecycleOwner = this

        setup()
    }

    abstract fun setup()
}