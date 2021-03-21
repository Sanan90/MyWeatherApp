package com.example.android.myweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myweatherapp.model.Repository
import com.example.android.myweatherapp.model.RepositoryImpl
import com.example.android.myweatherapp.viewmodel.AppState

class MinViewModel (
    val observerList : MutableLiveData<AppState> = MutableLiveData(),
    val repository : Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = observerList
    fun getWeatherCitiesList() = getcitiesListForWeather()

    fun getcitiesListForWeather() {
        observerList.value = AppState.Success(repository.getCitiesList())
    }
}

