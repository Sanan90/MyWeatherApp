package com.example.android.myweatherapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = "id"
const val CITY = "city"
const val TEMP = "temp"

@Entity
data class HistoryEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    val city : String = "",

    val temp: Int = 0,

    val condition : String = ""

)