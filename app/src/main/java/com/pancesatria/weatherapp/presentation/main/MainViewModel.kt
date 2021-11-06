package com.pancesatria.weatherapp.presentation.main

import androidx.lifecycle.MutableLiveData
import com.pancesatria.weatherapp.base.BaseViewModel
import com.pancesatria.weatherapp.data.source.repository.Repository
import com.pancesatria.weatherapp.data.model.City
import com.pancesatria.weatherapp.utils.Result
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Pance Satria on 11/4/2021.
 */
class MainViewModel @Inject constructor(
    private val repository: Repository
): BaseViewModel() {
    private var _cities = MutableLiveData<Result<List<City>>>()
    val cities = _cities

    fun getFavoriteCity(){
        _cities.value = Result.loading(null)
        repository.getAllCity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<City>>{
                override fun onSubscribe(d: Disposable) {
                    getCompositeDisposable()?.add(d)
                }

                override fun onNext(t: List<City>) {
                    _cities.value = Result.success(t)
                }

                override fun onError(e: Throwable) {
                    _cities.value = Result.error(e.message)
                }

                override fun onComplete() {
                }
            })
    }
}