package com.accubits.movie.data.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieList")
class MovieList {
    @PrimaryKey
    var id = 0
    var backdrop_path :String?=null
    var original_title = ""
    var original_language = ""
    var overview = ""
    var title = ""
    var vote_average :Double=0.0
    var poster_path :String?=null
    var adult = ""
    var release_date = ""
}