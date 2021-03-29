package com.accubits.movie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.accubits.movie.data.db.dao.MovieListDao
import com.accubits.movie.data.db.entities.MovieList


@Database(entities = [MovieList::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieListDao(): MovieListDao

    companion object {

        @Volatile //if use volatile immediate to display all the places
        private var instances: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instances
            ?: synchronized(LOCK) {
                instances ?: buildDatabase(context).also { instances = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "MovieApp.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }
}