package com.accubits.movie.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.accubits.movie.R
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

fun isInternetOn(aContext: Context): Boolean {
    val connectivityManager =
            aContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


fun showAlert(aContext: Context, aMessage: String) {
    try {
        val builder = AlertDialog.Builder(aContext)
        builder.setMessage(aMessage).setTitle(aContext.getString(R.string.app_name))
                .setCancelable(false).setPositiveButton("OK") { dialog, id ->
                    dialog.dismiss()
                }
        val alert = builder.create()
        alert.show()
        // Change the buttons color in dialog
        val pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
        pbutton.setTextColor(ContextCompat.getColor(aContext, R.color.purple_500))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}