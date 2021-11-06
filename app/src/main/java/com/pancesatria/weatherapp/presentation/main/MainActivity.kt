package com.pancesatria.weatherapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.pancesatria.weatherapp.R
import com.pancesatria.weatherapp.databinding.ActivityMainBinding
import com.pancesatria.weatherapp.presentation.search_result.SearchResultActivity
import com.pancesatria.weatherapp.utils.Config
import com.pancesatria.weatherapp.utils.Result
import com.pancesatria.weatherapp.utils.SnackbarUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        CityAdapter {
            gotoSearchActivity(it.name)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCity.adapter = adapter

        viewModel.getFavoriteCity()
        viewModel.cities.observe(this, {
            when (it.status) {
                Result.Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
                Result.Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    it.data?.let { cities ->
                        if (cities.isNotEmpty()) {
                            adapter.setData(cities)
                            binding.tvEmpty.visibility = View.GONE
                        } else {
                            binding.tvEmpty.visibility = View.VISIBLE
                        }
                    }
                }
                Result.Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message -> SnackbarUtil.show(binding.container, message) }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            isIconified = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(queryTxt: String?): Boolean {
                    clearFocus()
                    setQuery("", false)
                    onActionViewCollapsed()
                    queryTxt?.let {
                        gotoSearchActivity(it)
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }
            })
        }

        return true
    }

    override fun onDestroy() {
        viewModel.dispose()
        super.onDestroy()
    }

    fun gotoSearchActivity(cityName: String) {
        val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
        intent.putExtra(Config.KEY_CITY, cityName)
        startActivity(intent)
    }
}