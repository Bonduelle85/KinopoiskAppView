package com.example.kinopoiskappview.presentation.movielist.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.TouchDelegateInfoCompat
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.kinopoiskappview.R
import com.example.kinopoiskappview.databinding.MovieItemBinding
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Trailer
import com.example.kinopoiskappview.presentation.trailerlist.adapter.TrailerDiffCallback

class MovieAdapter(
    private val context: Context
) : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()), UpdateMovieList {

    private var onMovieClickListener: OnMovieClickListener? = null
    private var onReachEndListListener: OnReachEndListListener? = null

    fun setOnMovieClickListener (onMovieClickListener: OnMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener
    }

    fun setOnReachEndListListener (onReachEndListListener: OnReachEndListListener) {
        this.onReachEndListListener = onReachEndListListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        val kpRating = movieItem.kpRating
        val backgroundColor = setBackgroundColor(kpRating, holder)
        holder.binding.ratingTextView.background = backgroundColor
        holder.binding.ratingTextView.text = kpRating.toString()
        Glide.with(context)
            .load(movieItem.posterUrl)
            .into(holder.binding.posterImageView)

        holder.binding.root.setOnClickListener {
            onMovieClickListener?.onMovieClick(movieItem)
        }

        if (position == currentList.size - 1) {
            onReachEndListListener?.onReachEndList()
        }
    }

    private fun setBackgroundColor(kpRating: Double, holder: MovieViewHolder): Drawable? {
        val backgroundId = when {
            kpRating > 7 -> R.drawable.rating_background_high
            kpRating > 5 -> R.drawable.rating_background_medium
            else -> R.drawable.rating_background_low
        }
        val background = ContextCompat.getDrawable(holder.itemView.context, backgroundId)
        return background
    }

    override fun update(list: List<Movie>) = submitList(list)
}

fun interface OnMovieClickListener {
    fun onMovieClick(movie: Movie)
}

fun interface OnReachEndListListener {
    fun onReachEndList()
}

interface UpdateMovieList {
    fun update(list: List<Movie>)
}