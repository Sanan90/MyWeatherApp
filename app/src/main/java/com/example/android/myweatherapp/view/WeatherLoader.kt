package com.example.android.myweatherapp.view

import android.app.IntentService
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.example.android.myweatherapp.model.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val API_KEY = "e3aa6fb9-c620-4ae0-8af1-eb2d265bc279"
private val FORBIDDEN = "Forbidden"
const val RESULT_INFO = "RESULT_INFO"
const val CITY_TEMP = "CITY_TEMP"
const val CITY_FEELS_LIKE = "CITY_FEELS_LIKE"
const val CITY_CONDITION = "CITY_CONDITION"
const val CONNECT_IS_FAILED = "CONNECT_IS_FAILED"



private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)


class WeatherLoader(
    name: String = "DetailService"
) : IntentService(name) {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        val lat = intent?.getDoubleExtra(CITY_LAT, 0.0)
        val lon = intent?.getDoubleExtra(CITY_LON, 0.0)
        loadWeather(lat.toString(), lon.toString())
    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun loadWeather(lat : String, lon : String) {
        try {
            val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")

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

                    onResponce(weatherDTO)

                } catch (e: Exception) {
                    Log.e("", "Error", e)
                    e.printStackTrace()
                }   finally {
                    urlConnection?.disconnect()
                }
        }   catch (e: MalformedURLException) {
            Log.e("", "Error", e)
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    fun onResponce(weatherDTO: WeatherDTO) {
        val factDTO = weatherDTO.fact
        when(factDTO) {
            null -> onEmptyResponce()
            else -> onSuccessResponce(factDTO.temp, factDTO.feels_like, factDTO.condition)
        }
    }

    private fun onEmptyResponce() {
        resultInfo(CONNECT_IS_FAILED)
    }

    private fun onSuccessResponce(temp: Int?, feelsLike: Int?, condition: String?) {
        resultInfo(CONNECT_IS_SUCCESS)
        broadcastIntent.putExtra(CITY_TEMP, temp)
        broadcastIntent.putExtra(CITY_FEELS_LIKE, feelsLike)
        broadcastIntent.putExtra(CITY_CONDITION, condition)

        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun resultInfo(result : String) {
        broadcastIntent.putExtra(RESULT_INFO, result)
    }

    interface WeatherLoaderListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }

    }