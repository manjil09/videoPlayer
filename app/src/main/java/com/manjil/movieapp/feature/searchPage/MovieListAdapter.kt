package com.manjil.movieapp.feature.searchPage

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manjil.movieapp.R
import com.manjil.movieapp.databinding.ItemMovieBinding
import com.manjil.movieapp.interfaces.ItemOnClickListener
import com.manjil.movieapp.model.DataItem
import com.manjil.movieapp.model.MoviePojo

class MovieListAdapter(
//    private val movieList: ArrayList<MoviePojo>,
    private val movieList: List<DataItem?>?,
    private val onClickListener: ItemOnClickListener,
    private val context: Context
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val iconPath = "https://cdn.weatherbit.io/static/img/icons/"

    class ViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = movieList?.get(position)

        val title = data?.weather?.description
//            data.title

        val releaseYear = data?.validDate?.substring(0,4)
//            data.releaseYear

        val spannable = SpannableString("$title ($releaseYear)")
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#7F7E88")),
            title!!.length + 1,
            spannable.length,
            0
        )
        holder.binding.tvMovieTitle.setText(spannable, TextView.BufferType.SPANNABLE)
//        holder.binding.ivMoviePoster.setImageResource(data.poster)
        Glide
            .with(context)
            .load("$iconPath${data.weather.icon}.png")
            .placeholder(R.drawable.img_placeholder)
            .into(holder.binding.ivMoviePoster)
        holder.binding.itemMovieContainer.setOnClickListener {
            onClickListener.onItemClick(data)
        }
    }
}