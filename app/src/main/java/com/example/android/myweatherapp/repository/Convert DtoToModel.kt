package com.example.android.myweatherapp.repository

import com.example.android.myweatherapp.model.FactDTO
import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.model.WeatherDTO
import com.example.android.myweatherapp.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO) : List<Weather> {
    val fact : FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon))
}