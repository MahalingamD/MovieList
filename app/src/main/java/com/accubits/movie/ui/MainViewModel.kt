package com.accubits.movie.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.accubits.movie.data.db.entities.MovieList
import com.accubits.movie.utils.Coroutines
import com.maha.mvvmsimple.data.repository.MovieRepository

class MainViewModel(val mainViewModel: MovieRepository) : ViewModel() {


    var mMovieLiveData = MutableLiveData<ArrayList<HashMap<String, ArrayList<MovieList>>>>()


    fun getMovieList() {

        Coroutines.main {
            try {
                val aAllMovies = mainViewModel.getAllMovie()
                if (aAllMovies.isNullOrEmpty()) {
                    val aList = mainViewModel.getMovieList()
                    if (!aList.aMovileList.isNullOrEmpty()) {
                        mainViewModel.insertMovie(aList.aMovileList)
                    }
                }
                val aListsss = mainViewModel.getAllMovie()
                val aBestMovieList = mainViewModel.getBestMovie()
                val aNewMovieList = mainViewModel.getNewMovie()
                Log.e("Best Movie", "" + aBestMovieList.size)

                val aHashMapList = arrayListOf<HashMap<String, ArrayList<MovieList>>>()
                val aHashMap = hashMapOf<String, ArrayList<MovieList>>()
                aHashMap["New Release"] = ArrayList(aListsss)
                aHashMapList.add(aHashMap)

                val aHashMap1 = hashMapOf<String, ArrayList<MovieList>>()
                aHashMap1["New Video"] = ArrayList(aNewMovieList)
                aHashMapList.add(aHashMap1)

                val aHashMap2 = hashMapOf<String, ArrayList<MovieList>>()
                aHashMap2["Best Movies"] = ArrayList(aBestMovieList)
                aHashMapList.add(aHashMap2)

                mMovieLiveData.postValue(aHashMapList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}