package com.accubits.movie.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.accubits.movie.data.db.entities.MovieList

@Dao
interface MovieListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(aUser: List<MovieList>)

    @Query(" Select * from MovieList")
   suspend fun getAllMovie(): List<MovieList>

    @Query(" Select * from MovieList where vote_average > 5.0 ")
    suspend fun getBestMovie(): List<MovieList>

    @Query(" Select * from MovieList where backdrop_path IS NOT NULL ")
    suspend fun getNewMovie(): List<MovieList>


}