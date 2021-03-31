package com.accubits.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accubits.movie.R
import com.accubits.movie.data.db.entities.MovieList
import com.accubits.movie.utils.hide
import com.accubits.movie.utils.isInternetOn
import com.accubits.movie.utils.show
import com.accubits.movie.utils.showAlert
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


        if (isInternetOn(this)) {
            progress_bar.show()
            mMainViewModel.getMovieList()
        } else {
            showAlert(this, getString(R.string.alert_check_internet))
        }

        mMainViewModel.mMovieLiveData.observe(this, object : Observer<ArrayList<HashMap<String, ArrayList<MovieList>>>> {
            override fun onChanged(t: ArrayList<HashMap<String, ArrayList<MovieList>>>?) {
                progress_bar.hide()
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