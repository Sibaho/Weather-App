package com.pancesatria.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by Pance Satria on 11/6/2021.
 */
object DateHelper {
    private val sdf = SimpleDateFormat("dd MMM yyyy")

    fun getDate(epoch: Int): String {
        val date = Date(epoch.toLong() * 1000)
        if(compareDateToCurrent(sdf.format(date))){
            return "Today"
        }
        return sdf.format(date)
    }

    private fun compareDateToCurrent(date: String): Boolean{
        val today = Date()
        return sdf.format(today).equals(date)
    }
}