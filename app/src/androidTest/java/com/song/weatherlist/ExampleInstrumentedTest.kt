package com.song.weatherlist

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.song.weatherlist.data.City
import com.song.weatherlist.data.repository.WeatherRepository
import com.song.weatherlist.data.source.APIClient
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.song.weatherlist", appContext.packageName)
    }

    @Test
    fun test_get_weatherInfo() {
        val apiClient = APIClient.getRetrofitClient()
        val repository = WeatherRepository(apiClient)

        runBlocking {
            val seoul = City.Seoul
            val result = repository.getFiveDayWeatherInfo(seoul.lat, seoul.lon)
            assertNotNull(result)
        }
    }
}