package com.example.android.myweatherapp.repository

import com.example.android.myweatherapp.model.WeatherDTO
import retrofit2.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callBack: Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callBack)
    }
}