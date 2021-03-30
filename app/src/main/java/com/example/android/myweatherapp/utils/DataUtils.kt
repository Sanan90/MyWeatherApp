package com.example.android.myweatherapp.utils

import com.example.android.myweatherapp.model.City
import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.room.HistoryEntity

fun convertHistoryEntityToWeather(entityList : List<HistoryEntity>) : List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temp, 0, it.condition )
    }
}

fun convertWeatherToEntity(weather: Weather) : HistoryEntity {
    return HistoryEntity(0, weather.city.name, weather.temperature, weather.condition )
}