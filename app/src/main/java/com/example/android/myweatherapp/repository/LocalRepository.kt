package com.example.android.myweatherapp.repository

import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.room.HistoryDao

interface LocalRepository {
    fun getAllHistory() : List<Weather>
    fun saveEntity(weather: Weather)
}