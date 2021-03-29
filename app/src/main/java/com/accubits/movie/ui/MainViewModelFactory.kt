package com.accubits.movie.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maha.mvvmsimple.data.repository.MovieRepository


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val aActivity: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(MovieRepository(aActivity)) as T
    }
}