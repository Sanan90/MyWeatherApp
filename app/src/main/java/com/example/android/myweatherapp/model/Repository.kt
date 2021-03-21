package com.example.android.myweatherapp.model

interface Repository {
    fun getCitiesList() : List <Weather>
}