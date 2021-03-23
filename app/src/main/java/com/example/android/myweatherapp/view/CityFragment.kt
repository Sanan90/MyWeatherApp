package com.example.android.myweatherapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android.myweatherapp.databinding.FragmentCityBinding
import com.example.android.myweatherapp.model.FactDTO
import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.model.WeatherDTO

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val CONNECT_IS_SUCCESS = "CONNECT IS SUCCESS"
const val THREADS_FRAGMENT_BROADCAST_EXTRA = "THREADS FRAGMENT EXTRA"
const val CITY_LAT = "CityLat"
const val CITY_LON = "CityLot"

class CityFragment : Fragment() {

    var _binding: FragmentCityBinding? = null
    val binding get() = _binding!!
    private lateinit var weather: Weather

    private val broadcastReceiver : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            when (intent.getStringExtra(RESULT_INFO)) {
                CONNECT_IS_FAILED ->  connectIsFailed(intent.getStringExtra(CONNECT_IS_FAILED)!!)
                CONNECT_IS_SUCCESS ->  {
                    onLoaderListener.onLoaded(
                        weatherDTO = WeatherDTO(
                            fact = FactDTO(
                                intent.getIntExtra(CITY_TEMP, -100),
                                intent.getIntExtra(CITY_FEELS_LIKE, -100),
                                intent.getStringExtra(CITY_CONDITION))))
                }
                else -> someError()
            }
        }
    }

    private val onLoaderListener : WeatherLoader.WeatherLoaderListener = object : WeatherLoader.WeatherLoaderListener {

        override fun onLoaded(weatherDTO: WeatherDTO) {
            displayWeather(weatherDTO)
        }

        override fun onFailed(throwable: Throwable) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireContext().let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(broadcastReceiver, IntentFilter(DETAILS_INTENT_FILTER))
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weather = arguments?.getParcelable(CHOOSE_CITY) ?: Weather()

        val loader = WeatherLoader()

        initMainService()

//        loader.loadWeather()

        binding.cityFragmentName.text = weather.city.name
//        binding.cityFragmentTemp.text = weather.temperature.toString()
//        binding.FLike.text = weather.like.toString()

    }

    private fun initMainService() {
        requireContext().let {
            it.startService(Intent(it, WeatherLoader::class.java).apply {
                putExtra(CITY_LAT, weather.city.lat)
                putExtra(CITY_LON, weather.city.lon)
            })
        }
    }

//    private fun initView(weather: Weather) {
//        binding.cityFragmentName.text = weather.city.name
//        binding.cityFragmentTemp.text = weather.temperature.toString()
//        binding.FLike.text = weather.like.toString()
//    }

    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
            val city = weather.city
            cityFragmentName.text = city.name
            cityFragmentTemp.text = weatherDTO.fact?.temp.toString()
            FLike.text = weatherDTO.fact?.feels_like.toString()
            val aboutWeather : String = aboutWeatherInfo(weatherDTO.fact?.condition.toString())
            aboutWeatherInfoOnDisplay.text = aboutWeather
            aboutConnect.text = "Connecting"
        }
    }

    private fun connectIsFailed(connectFailed : String) {
        binding.aboutConnect.text = "Connect is failed"
    }

    private fun someError() {
        binding.aboutConnect.text = "Connect is failed"
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }

    companion object {
        val CHOOSE_CITY = "CHOOSE_CITY"

        fun newInstance(bundle: Bundle): CityFragment {
            val fragment = CityFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}