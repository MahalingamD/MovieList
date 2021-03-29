package com.maha.mvvmsimple.data.repository

import android.content.Context
import com.accubits.movie.data.db.AppDatabase
import com.accubits.movie.data.db.entities.MovieList
import com.accubits.movie.data.network.ApiClient
import com.accubits.movie.data.network.SafeApiRequest
import com.accubits.movie.data.network.response.MovieResponse


class MovieRepository(val aContext: Context) : SafeApiRequest() {

    suspend fun getMovieList(): MovieResponse {

        val aApi = ApiClient.getService()

        return apiRequest {
            aApi.getRestaurantList(
                "9c0523bff54071c4fb4b716a950231b9",
                "en-US", "1", "US", "2"
            )
        }
    }


    suspend fun insertMovie(aList: List<MovieList>) {
        AppDatabase(aContext).getMovieListDao().insertAllMovie(aList)
    }


    suspend fun getAllMovie(): List<MovieList> {
      return  AppDatabase(aContext).getMovieListDao().getAllMovie()
    }

    suspend fun getBestMovie(): List<MovieList> {
        return  AppDatabase(aContext).getMovieListDao().getBestMovie()
    }

    suspend fun getNewMovie(): List<MovieList> {
        return  AppDatabase(aContext).getMovieListDao().getNewMovie()
    }


}