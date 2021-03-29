package org.codejudge.application.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accubits.movie.R
import com.accubits.movie.adapter.MovieAdapter
import com.accubits.movie.data.db.entities.MovieList
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class CategoryAdapter(
    val mContext: Context,
    var mMovieList: ArrayList<HashMap<String, ArrayList<MovieList>>>
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val aHeaderText: AppCompatTextView = itemView.findViewById(R.id.category_title)
        private val aChildRecyclerView: RecyclerView =
            itemView.findViewById(R.id.child_recyclerview)


        val myLayoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)

        val mCategoryAdapter = MovieAdapter(mContext, arrayListOf())

        @SuppressLint("SetTextI18n")
        fun bindItems(position: Int) {
            // val aMovie = mMovieList[position]
            val aMovie = mMovieList[position]
            val alist = aMovie.keys
            aHeaderText.text = alist.elementAt(0)

            aChildRecyclerView.layoutManager = myLayoutManager
            aChildRecyclerView.setHasFixedSize(true)
            aChildRecyclerView.adapter = mCategoryAdapter
            when (position) {
                0 -> {
                    aMovie[alist.elementAt(0)]?.let { mCategoryAdapter.update(it) }
                }
                1 -> {
                    aMovie[alist.elementAt(0)]?.let { mCategoryAdapter.update(it) }
                }
                else -> {
                    aMovie[alist.elementAt(0)]?.let { mCategoryAdapter.update(it) }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_recycler_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(position)
    }


    fun update(it: ArrayList<HashMap<String, ArrayList<MovieList>>>) {

        mMovieList.addAll(it)
        notifyDataSetChanged()
    }


    fun loadFromUrlGlide(url: String, aSwitchImg: ImageView) {

        Glide.with(mContext).load(url).fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(aSwitchImg)

    }
}