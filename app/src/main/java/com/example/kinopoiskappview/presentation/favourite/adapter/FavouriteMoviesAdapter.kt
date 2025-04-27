package com.example.kinopoiskappview.presentation.favourite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.kinopoiskappview.R
import com.example.kinopoiskappview.databinding.FavouriteItemBinding
import com.example.kinopoiskappview.domain.model.Movie

class FavouriteMoviesAdapter : ListAdapter<Movie, FavouriteViewHolder>(FavoriteDiffCallback()),
    UpdateFavouriteList {

    private var onFavouriteClickListener: OnFavouriteClickListener? = null

    fun setOnFavouriteMovieClickListener(onFavouriteClickListener: OnFavouriteClickListener) {
        this.onFavouriteClickListener = onFavouriteClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavouriteItemBinding.inflate(layoutInflater, parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val context = holder.binding.root.context
        val movieItem = getItem(position)
        val kpRating = movieItem.kpRating
        with(holder.binding) {
            ratingTextView.text = movieItem.kpRating.toString()
            ratingTextView.setTextColor(color(kpRating, context))
            nameTextView.text = movieItem.name
            nameTextView.isSelected = true
            yearTextView.text = movieItem.year
            genresTextView.text = movieItem.genres.joinToString(" ")
            descriptionTextView.text = movieItem.description

            Glide.with(root.context)
                .load(movieItem.posterUrl)
                .into(holder.binding.posterImageView)

            root.setOnClickListener {
                onFavouriteClickListener?.onMovieClick(movieItem)
            }
        }
    }

    override fun update(list: List<Movie>) = submitList(list)

    private fun color(kpRating: Double, context: Context): Int {
        val colorRes = when {
            kpRating > 7 -> R.color.rating_high_color
            kpRating > 5 -> R.color.rating_medium_color
            else -> R.color.rating_low_color
        }
        return ContextCompat.getColor(context, colorRes)
    }
}

fun interface OnFavouriteClickListener {
    fun onMovieClick(movie: Movie)
}

interface UpdateFavouriteList {
    fun update(list: List<Movie>)
}