package com.example.kinopoiskappview.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.kinopoiskappview.databinding.MovieItemBinding
import com.example.kinopoiskappview.domain.model.Movie

class MovieAdapter(
    private val context: Context
) : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    var onMovieClickListener: OnMovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.binding.ratingTextView.text = movieItem.kpRating.toString()
        Glide.with(context)
            .load(movieItem.posterUrl)
            .into(holder.binding.posterImageView)

        holder.binding.root.setOnClickListener {
            onMovieClickListener?.onMovieClick(movieItem)
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}