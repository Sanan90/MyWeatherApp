package com.example.android.myweatherapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.myweatherapp.R
import com.google.android.material.textview.MaterialTextView
import com.example.android.myweatherapp.model.Weather

class MainFragmentAdapter (private var onItemViewClickListener: OnItemViewClickListener)
    : RecyclerView.Adapter<MainFragmentAdapter.MyViewHolder>() {

    private var weatherData: List<Weather> = listOf()

    fun setWeather(list: List<Weather>) {
        weatherData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_item, parent, false)
         as View )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }



    override fun getItemCount() = weatherData.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weather: Weather) {
            itemView.apply {
                findViewById<MaterialTextView>(R.id.cityName).text = weather.city.name
                setOnClickListener { onItemViewClickListener.onItemViewClick(weather) }
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(weather: Weather)
    }
}

