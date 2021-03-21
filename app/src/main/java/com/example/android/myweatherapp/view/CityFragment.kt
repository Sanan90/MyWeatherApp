package com.example.android.myweatherapp.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.android.myweatherapp.databinding.FragmentCityBinding
import com.example.android.myweatherapp.databinding.FragmentMainBinding
import com.example.android.myweatherapp.model.City
import com.example.android.myweatherapp.model.Weather
import com.example.android.myweatherapp.model.WeatherDTO
import kotlinx.android.synthetic.main.recycle_item.*

class CityFragment : Fragment() {

    var _binding: FragmentCityBinding? = null
    val binding get() = _binding!!
    private lateinit var weather: Weather
    private val onLoaderListener : WeatherLoader.WeatherLoaderListener = object : WeatherLoader.WeatherLoaderListener {

        override fun onLoaded(weatherDTO: WeatherDTO) {
            displayWeather(weatherDTO)
        }

        override fun onFailed(throwable: Throwable) {

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

        val loader = WeatherLoader(onLoaderListener, weather.city.lat, weather.city.lon )
        loader.loadWeather()

        binding.cityFragmentName.text = weather.city.name
//        binding.cityFragmentTemp.text = weather.temperature.toString()
//        binding.FLike.text = weather.like.toString()

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
        }
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