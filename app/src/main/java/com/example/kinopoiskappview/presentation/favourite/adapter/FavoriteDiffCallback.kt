package com.example.kinopoiskappview.presentation.favourite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kinopoiskappview.domain.model.Movie

class FavoriteDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.kpRating == newItem.kpRating &&
                oldItem.year == newItem.year &&
                oldItem.description == newItem.description &&
                oldItem.genres.joinToString(" ") == newItem.genres.joinToString(" ")
    }
}