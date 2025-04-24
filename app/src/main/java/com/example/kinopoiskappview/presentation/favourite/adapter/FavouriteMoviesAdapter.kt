package com.example.kinopoiskappview.presentation.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.kinopoiskappview.databinding.FavouriteItemBinding
import com.example.kinopoiskappview.domain.model.Movie

class FavouriteMoviesAdapter : ListAdapter<Movie, FavouriteViewHolder>(FavoriteDiffCallback()), UpdateFavouriteList {

    private var onFavouriteClickListener: OnFavouriteClickListener? = null

    fun setOnFavouriteMovieClickListener (onFavouriteClickListener: OnFavouriteClickListener) {
        this.onFavouriteClickListener = onFavouriteClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavouriteItemBinding.inflate(layoutInflater, parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val movieItem = getItem(position)

        holder.binding.ratingTextView.text = movieItem.kpRating.toString()
        holder.binding.nameTextView.text = movieItem.name
        holder.binding.yearTextView.text = movieItem.year
        holder.binding.genresTextView.text = movieItem.genres.joinToString(" ")
        holder.binding.descriptionTextView.text = movieItem.description

        Glide.with(holder.binding.root.context)
            .load(movieItem.posterUrl)
            .into(holder.binding.posterImageView)

        holder.binding.root.setOnClickListener {
            onFavouriteClickListener?.onMovieClick(movieItem)
        }
    }

    override fun update(list: List<Movie>) = submitList(list)
}

fun interface OnFavouriteClickListener {
    fun onMovieClick(movie: Movie)
}

interface UpdateFavouriteList {
    fun update(list: List<Movie>)
}