package com.accubits.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accubits.movie.R
import com.accubits.movie.data.db.entities.MovieList
import kotlinx.android.synthetic.main.activity_main.*
import org.codejudge.application.adapter.CategoryAdapter

class MainActivity : AppCompatActivity() {

    lateinit var mMainViewModel: MainViewModel

    lateinit var mCategoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel =
            ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)

        setAdapter()
        mMainViewModel.getMovieList()

        mMainViewModel.mMovieLiveData.observe(this, object : Observer<ArrayList<HashMap<String,ArrayList<MovieList>>>> {
            override fun onChanged(t: ArrayList<HashMap<String,ArrayList<MovieList>>>?) {

                if (!t.isNullOrEmpty())
                    mCategoryAdapter.update(t)
            }

        })

    }


    private fun setAdapter() {
        try {
            val myLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            newmovie_recycler.layoutManager = myLayoutManager
            newmovie_recycler.setHasFixedSize(true)

            mCategoryAdapter = CategoryAdapter(this, arrayListOf())
            newmovie_recycler.adapter = mCategoryAdapter

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}