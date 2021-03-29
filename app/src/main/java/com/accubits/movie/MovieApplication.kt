package com.accubits.movie

import android.app.Application
import com.accubits.movie.data.db.AppDatabase

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.invoke(this)
    }
}