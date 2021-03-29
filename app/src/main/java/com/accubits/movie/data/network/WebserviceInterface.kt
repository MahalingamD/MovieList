package com.accubits.movie.data.network

import com.accubits.movie.data.network.response.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebserviceInterface {

    @GET("3/movie/upcoming")
    suspend fun getRestaurantList(
        @Query("api_key") aKey: String,
        @Query("language") aLanguage: String,
        @Query("page") aPage: String,
        @Query("region") aRegion: String,
        @Query("with_release_type") aType: String
    ): Response<MovieResponse>


}