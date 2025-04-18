package com.example.kinopoiskappview.presentation.movielist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kinopoiskappview.domain.model.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}