package com.song.weatherlist.di

import com.song.weatherlist.data.source.APIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideWeatherService() = APIClient.getRetrofitClient()
}