package com.pancesatria.weatherapp.presentation.search_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pancesatria.weatherapp.base.BaseViewModel
import com.pancesatria.weatherapp.data.source.repository.Repository
import com.pancesatria.weatherapp.data.model.ForecastResponse
import com.pancesatria.weatherapp.utils.Result
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Pance Satria on 11/5/2021.
 */
class SearchResultViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private var _forecast = MutableLiveData<Result<ForecastResponse>>()
    val forecast: LiveData<Result<ForecastResponse>> = _forecast

    private val _insertCity = MutableLiveData<Result<Long>>()
    val insertCity:LiveData<Result<Long>> = _insertCity

    fun getWeather(city: String) {
        _forecast.value = Result.loading(null)
        repository.getNext3DaysForecastByCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ForecastResponse> {
                override fun onSubscribe(d: Disposable) {
                    getCompositeDisposable()?.add(d)
                }

                override fun onNext(t: ForecastResponse) {
                    _forecast.value = Result.success(t)

                }

                override fun onError(e: Throwable) {
                    _forecast.value = Result.error(e.message)
                    e.printStackTrace()
                }

                override fun onComplete() {
                }
            })
    }

    fun addToCityFavorite() {
        _insertCity.value = Result.loading(null)
        val city = _forecast.value?.data?.city
        Observable.fromCallable { city?.let { repository.insertCity(it) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long>{
                override fun onSubscribe(d: Disposable) {
                    getCompositeDisposable()?.add(d)
                }

                override fun onNext(t: Long) {
                    _insertCity.value = Result.success(t)
                }

                override fun onError(e: Throwable) {
                    _insertCity.value = Result.error(e.message)
                    e.printStackTrace()
                }

                override fun onComplete() {
                }
            })

    }
}