package com.example.kinopoiskappview.presentation.trailerlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Trailer

class TrailerDiffCallback : DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.trailerName == newItem.trailerName
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}