package com.example.kinopoiskappview.presentation.reviewlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.kinopoiskappview.R
import com.example.kinopoiskappview.data.ReviewType
import com.example.kinopoiskappview.databinding.ReviewItemBinding
import com.example.kinopoiskappview.domain.model.Review

class ReviewAdapter : ListAdapter<Review, ReviewViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ReviewItemBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val trailerItem = getItem(position)
        val context = holder.binding.root.context
        val color = setItemColor(trailerItem, context)
        with(holder.binding) {
            authorTextView.text = trailerItem.author
            createdAtTextView.text = trailerItem.createdAt
            titleTextView.text = trailerItem.title
            reviewTextView.text = trailerItem.review
            likesTextView.text =
                context.getString(R.string.review_likes, trailerItem.likes.toString())
            dislikesTextView.text =
                context.getString(R.string.review_dislikes, trailerItem.dislikes.toString())
            reviewItemLayout.setBackgroundColor(color)
        }
    }

    private fun setItemColor(trailerItem: Review, context: Context) = ContextCompat.getColor(
        context,
        when (trailerItem.type) {
            ReviewType.POSITIVE -> R.color.review_positive_color
            ReviewType.NEGATIVE -> R.color.review_negative_color
            ReviewType.NEUTRAL -> R.color.review_neutral_color
        }
    )
}