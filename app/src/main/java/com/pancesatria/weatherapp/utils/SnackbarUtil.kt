package com.pancesatria.weatherapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Pance Satria on 11/6/2021.
 */
object SnackbarUtil {
    fun show(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}