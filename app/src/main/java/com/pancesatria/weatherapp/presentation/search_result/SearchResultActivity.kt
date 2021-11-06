package com.pancesatria.weatherapp.presentation.search_result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.pancesatria.weatherapp.databinding.ActivitySearchResultBinding
import com.pancesatria.weatherapp.utils.Config
import com.pancesatria.weatherapp.utils.Result
import com.pancesatria.weatherapp.utils.SnackbarUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SearchResultViewModel

    private lateinit var binding: ActivitySearchResultBinding

    private val adapter by lazy { ForecastAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvForecast.adapter = adapter

        intent.extras?.let {
            val city = it.getString(Config.KEY_CITY, Config.EMPTY_STRING)
            viewModel.getWeather(city)
            supportActionBar?.title = city.uppercase()
        }

        viewModel.forecast.observe(this, {
            when (it.status) {
                Result.Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
                Result.Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    it.data?.let { forecast ->
                        binding.btnAddToFavorite.visibility = View.VISIBLE
                        adapter.setData(forecast.list)
                    }
                }
                Result.Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message -> SnackbarUtil.show(binding.container, message) }
                }
            }
        })

        viewModel.insertCity.observe(this, {
            when (it.status) {
                Result.Status.LOADING -> {
                }
                Result.Status.SUCCESS -> {
                    binding.btnAddToFavorite.isEnabled = false
                    SnackbarUtil.show(binding.container, "Added to favorite")
                }
                Result.Status.ERROR -> {
                    it.message?.let { message -> SnackbarUtil.show(binding.container, message) }
                }
            }
        })

        binding.btnAddToFavorite.setOnClickListener {
            viewModel.addToCityFavorite()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        viewModel.dispose()
        super.onDestroy()
    }
}