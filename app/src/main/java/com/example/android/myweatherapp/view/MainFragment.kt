package com.example.android.myweatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.myweatherapp.R
import com.example.android.myweatherapp.viewmodel.AppState
import com.example.android.myweatherapp.viewmodel.MinViewModel
import com.example.android.myweatherapp.databinding.FragmentMainBinding
import com.example.android.myweatherapp.model.Weather
import kotlinx.android.synthetic.main.activity_main.view.*

class MainFragment : Fragment() {

    var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!
    private val viewModel: MinViewModel by lazy {
        ViewModelProvider(this).get(
            MinViewModel::class.java
        )
    }

    private val adapter =
        MainFragmentAdapter(object :
            MainFragmentAdapter.OnItemViewClickListener {
            override fun onItemViewClick(weather: Weather) {
                activity?.supportFragmentManager?.beginTransaction()!!.replace(
                    R.id.container, CityFragment.newInstance(Bundle()
                        .apply { putParcelable(CityFragment.CHOOSE_CITY, weather) }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.citiesList.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherCitiesList()
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                adapter.setWeather(appState.list)
            }
            is AppState.Error -> {
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}