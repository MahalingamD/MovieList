package com.accubits.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.accubits.movie.R
import com.accubits.movie.data.db.entities.MovieList
import com.accubits.movie.utils.convertDateTime
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class MovieAdapter(val mContext: Context, var mMovieList: ArrayList<MovieList>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val aHeaderText: TextView = itemView.findViewById(R.id.movie_name)
        private val ayearText: TextView = itemView.findViewById(R.id.movie_year)

        private val aMovieImg: AppCompatImageView = itemView.findViewById(R.id.movie_img)

        @SuppressLint("SetTextI18n")
        fun bindItems(position: Int) {

            val aMovie = mMovieList[position]
            aHeaderText.text = aMovie.title
            ayearText.text = aMovie.release_date

            ayearText.text = convertDateTime(aMovie.release_date,"yyyy-MM-dd","yyyy")

            if(aMovie.backdrop_path!=null){
                    val aPath="http://image.tmdb.org/t/p/w185${aMovie.backdrop_path}"
                loadFromUrlGlide(aPath,aMovieImg)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(position)
    }

    fun updateAll(it: ArrayList<MovieList>) {
        mMovieList.clear()
        mMovieList.addAll(it)
        notifyDataSetChanged()
    }

    fun update(it: ArrayList<MovieList>) {

        mMovieList.addAll(it)
        notifyDataSetChanged()
    }


    fun loadFromUrlGlide(url: String, aSwitchImg: ImageView) {

            Glide.with(mContext).load(url).fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(aSwitchImg)

    }
}