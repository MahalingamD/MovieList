package com.accubits.movie.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(aMsg: String) {
    Toast.makeText(this, aMsg, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {

    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->

        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

const val DATE_TIME_FORMAT = "yyyy-MM-dd"
fun getCurrentTime(): String {
    var aCurrentDateSTR = ""
    aCurrentDateSTR = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(Date())
    return aCurrentDateSTR
}



fun convertDateTime(aDataValue: String, originaL_DATETIME_FORAMT: String,
                    fieldFormat: String?): String {
    var dateString = aDataValue
    try {
        if (aDataValue.isNotEmpty()) {
            val dateFormat = SimpleDateFormat(originaL_DATETIME_FORAMT, Locale.getDefault())
            val dateFormat1 =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val targetFormat = SimpleDateFormat(fieldFormat, Locale.getDefault())
            dateFormat.isLenient = false
            targetFormat.isLenient = false
            var convertedDate: Date

            try {
                convertedDate = dateFormat.parse(dateString) as Date
                dateString = targetFormat.format(convertedDate)
                //   Log.e("Converted date", formattedDate)
            } catch (e: ParseException) {
                convertedDate = dateFormat1.parse(dateString) as Date
                dateString = targetFormat.format(convertedDate)
            }
        }
    } catch (ex: java.lang.Exception) {
        ex.printStackTrace()
    }
    return dateString
}