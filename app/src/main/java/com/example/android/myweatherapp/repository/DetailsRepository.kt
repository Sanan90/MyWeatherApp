package com.example.android.myweatherapp.repository

import com.example.android.myweatherapp.model.WeatherDTO

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat : Double,
        lon : Double,
        cardData : retrofit2.Callback<WeatherDTO>
    )
}