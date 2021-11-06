package com.pancesatria.weatherapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pancesatria.weatherapp.data.model.City
import com.pancesatria.weatherapp.databinding.ItemCityBinding

/**
 * Created by Pance Satria on 11/6/2021.
 */
class CityAdapter(private val onClick: (City) -> Unit) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    private var cities = listOf<City>()

    fun setData(cities: List<City>) {
        this.cities = cities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.ViewHolder {
        return ViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int = cities.size

    inner class ViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.city = city
            binding.root.setOnClickListener {
                onClick(city)
            }
        }
    }
}