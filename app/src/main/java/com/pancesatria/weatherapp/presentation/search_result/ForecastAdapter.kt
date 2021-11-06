package com.pancesatria.weatherapp.presentation.search_result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pancesatria.weatherapp.R
import com.pancesatria.weatherapp.databinding.ItemForecastBinding
import com.pancesatria.weatherapp.data.model.Forecast
import com.pancesatria.weatherapp.utils.Config
import com.pancesatria.weatherapp.utils.DateHelper

/**
 * Created by Pance Satria on 11/5/2021.
 */
class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private var forecastList = listOf<Forecast>()

    fun setData(forecastList: List<Forecast>) {
        this.forecastList = forecastList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapter.ViewHolder {
        return ViewHolder(
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: ForecastAdapter.ViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size

    inner class ViewHolder(private val binding: ItemForecastBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Forecast) {
            val tempInCelsius = forecast.temp.day - 273.15
            val tempAnDescription = forecast.weather[0].main.plus(Config.SPACE)
                .plus(context.getString(R.string.temperature, tempInCelsius))

            binding.tvDay.text = DateHelper.getDate(forecast.dt)
            binding.tvHumadity.text = context.getString(R.string.humadity, forecast.humidity.toString())
            binding.tvTemperature.text = tempAnDescription
            binding.tvWind.text = context.getString(R.string.wind, forecast.speed.toString())
        }
    }
}