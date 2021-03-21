package com.example.android.myweatherapp.viewmodel

import com.example.android.myweatherapp.model.Weather

sealed class AppState {
    data class Success (val list: List<Weather>) : AppState()
    data class Error (val throwable: Throwable) : AppState()
    object Loading : AppState()
}