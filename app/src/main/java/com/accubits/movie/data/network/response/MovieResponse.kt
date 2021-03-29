package com.accubits.movie.data.network.response

import com.accubits.movie.data.db.entities.MovieList
import com.google.gson.annotations.SerializedName

class MovieResponse {

    var page = 0

    @SerializedName("results")
    var aMovileList: List<MovieList> = listOf()




}