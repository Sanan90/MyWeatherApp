package com.example.android.myweatherapp.view

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.model.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val API_KEY = "e3aa6fb9-c620-4ae0-8af1-eb2d265bc279"

@RequiresApi(Build.VERSION_CODES.N)
class WeatherLoader(
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val lon: Double
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadWeather() {
        try {
            val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            val handler = Handler(Looper.getMainLooper())
            Thread {
                var urlConnection : HttpsURLConnection? = null
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection

                    urlConnection.requestMethod = "GET"

                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key", API_KEY
                    )
                    urlConnection.readTimeout = 10000

                    val buffereReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val json= getLines(buffereReader)
                    val weatherDTO : WeatherDTO = Gson().fromJson(json, WeatherDTO::class.java)


                    handler.post{listener.onLoaded(weatherDTO)}
                } catch (e: Exception) {
                    Log.e("", "Error", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                }   finally {
                    urlConnection?.disconnect()
                }
            }.start()
        }   catch (e: MalformedURLException) {
            Log.e("", "Error", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface WeatherLoaderListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }
}