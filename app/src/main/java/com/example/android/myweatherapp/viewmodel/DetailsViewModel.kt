package com.example.android.myweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.model.WeatherDTO
import com.example.android.myweatherapp.repository.*
import com.example.android.myweatherapp.viewmodel.App.Companion.getHistoryDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel (
    private val `detailLiveData`: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
    private val historyRepositoryImpl : LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {
    fun getLiveData() = `detailLiveData`

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        `detailLiveData`.value = AppState.Loading
        detailsRepositoryImpl.getWeatherDetailsFromServer(lat, lon, callBack)
    }

    fun saveWeatherOnDb(weather: Weather) {
        historyRepositoryImpl.saveEntity(weather)
    }

    private val callBack = object : Callback<WeatherDTO> {

        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            val serverResponse: WeatherDTO? = response.body()
            `detailLiveData`.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            `detailLiveData`.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: WeatherDTO): AppState {
            val fact = serverResponse.fact
            return if (fact == null || fact.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertDtoToModel(serverResponse))
            }
        }
    }
}
