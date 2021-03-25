package com.example.android.myweatherapp.repository

import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.model.WeatherDTO
import retrofit2.Call
import retrofit2.http.*
import java.net.URL

//https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}


interface WeatherAPI {

    @GET ("v2/informers")
    fun getWeather (
        @Header("X-Yandex-API-Key") key : String,
        @Query("lat") lat : Double,
        @Query("lon") lon : Double
    ) : Call<WeatherDTO>



    //    "//https://api.weather.yandex.ru/v2/films/movie_id"
    @GET("v2/films/{movie_id}")
    fun getFilm (
        @Path("movie_id") movie_id : String
    ) : Call<List<Weather>>
}