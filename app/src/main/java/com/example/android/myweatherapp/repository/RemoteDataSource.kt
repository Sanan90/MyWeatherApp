package com.example.android.myweatherapp.repository

import com.example.android.myweatherapp.model.WeatherDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Callback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val API_KEY = "e3aa6fb9-c620-4ae0-8af1-eb2d265bc279"

class RemoteDataSource {

    private val weatherAPI = Retrofit
        .Builder()
        .baseUrl("https://api.weather.yandex.ru/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(createOkHttpClient(WeatherAPIInterceptor()))
        .build()
        .create(WeatherAPI::class.java)

    fun getWeatherDetails(lat : Double, lon : Double, callback : retrofit2.Callback<WeatherDTO>  ) {
        weatherAPI.getWeather(API_KEY, lat, lon).enqueue(callback)
    }

    private fun createOkHttpClient(interceptor : Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    inner class WeatherAPIInterceptor : Interceptor {
        @Throws (IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

//            val req = chain.request().newBuilder().addHeader("sfds", "asfd").build()
//            val res = chain.proceed(req)
            return  chain.proceed(chain.request())
        }
    }
}