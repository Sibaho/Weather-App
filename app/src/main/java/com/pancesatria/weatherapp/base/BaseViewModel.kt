package com.pancesatria.weatherapp.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Pance Satria on 11/5/2021.
 */
abstract class BaseViewModel: ViewModel() {
    private var compositeDisposable: CompositeDisposable? = null

    fun getCompositeDisposable(): CompositeDisposable? {
        if (compositeDisposable == null || compositeDisposable?.isDisposed == true) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable
    }

    fun dispose(){
        compositeDisposable?.dispose()
    }
}