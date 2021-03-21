package com.example.android.myweatherapp.model

import android.os.Parcelable
import com.example.android.myweatherapp.model.City
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getMainCity(),
    val temperature: Int = 0,
    val like: Int = 0
) : Parcelable

fun getMainCity() = City(
    "Клин",
    1564324.42342,
    156.21412345
)

fun getcities() =
    listOf(
        Weather(City("Москва", 55.755826, 37.617299900000035), 1, 2),
        Weather(City("Клин", 56.331588, 36.728725), 1, 2),
        Weather(City("Гянджа", 40.673930, 46.355144), 1, 2),
        Weather(City("Баку", 40.372967, 49.853139), 1, 2),
        Weather(City("Пенза", 53.195042, 45.018316), 1, 2),
        Weather(City("Санкт Петербург", 59.938951, 30.315635), 1, 2),
        Weather(City("Ставрополь", 45.043311, 41.969110), 1, 2),
        Weather(City("Тверь", 56.859625, 35.911851), 1, 2),
        Weather(City("Астана", 51.128207, 71.430411), 1, 2),
        Weather(City("Ярославль", 57.626559, 39.893804), 1, 2),
        Weather(City("Ульяновск", 54.314192, 48.403123), 1, 2),

        Weather(City("Новосибирск", 55.030199, 82.920430), 1, 2),
        Weather(City("Пунта-Кана", 18.567254, -68.361515), 1, 2),
        Weather(City("Бангкок", 13.771370, 100.513782), 1, 2),
        Weather(City("Нью-йорк", 40.714599, -74.002791), 1, 2),
        Weather(City("Сидней", -33.865248, 151.216484), 1, 2),
        Weather(City("Мехико", 19.432605, -99.133296), 1, 2),
        Weather(City("Сочи", 43.585472, 39.723089), 1, 2),
        Weather(City("Норильск", 69.343985, 88.210384), 1, 2),
        Weather(City("Черапунджи", 25.284904, 91.718431), 1, 2),
        Weather(City("Маракайбо", 10.642735, -71.640913), 1, 2)
    )

