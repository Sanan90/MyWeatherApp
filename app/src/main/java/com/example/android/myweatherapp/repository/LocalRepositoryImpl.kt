package com.example.android.myweatherapp.repository

import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.room.HistoryDao
import com.example.android.myweatherapp.utils.convertHistoryEntityToWeather
import com.example.android.myweatherapp.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource : HistoryDao) : LocalRepository {
     
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}