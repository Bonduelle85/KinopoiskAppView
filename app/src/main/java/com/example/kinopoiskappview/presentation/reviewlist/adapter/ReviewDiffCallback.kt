package com.example.kinopoiskappview.presentation.reviewlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kinopoiskappview.domain.model.Review

class ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}