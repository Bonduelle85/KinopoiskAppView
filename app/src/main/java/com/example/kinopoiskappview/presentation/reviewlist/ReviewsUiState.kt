package com.example.kinopoiskappview.presentation.reviewlist

import android.view.View
import com.example.kinopoiskappview.databinding.FragmentReviewListBinding
import com.example.kinopoiskappview.domain.model.Review
import com.example.kinopoiskappview.presentation.reviewlist.adapter.UpdateReviewList

sealed interface ReviewsUiState {

    fun show(binding: FragmentReviewListBinding)
    fun showList(updateReviewList: UpdateReviewList)

    object Loading : ReviewsUiState {

        override fun show(binding: FragmentReviewListBinding) {
            binding.progressBar.visibility = View.VISIBLE
            binding.errorLayout.visibility = View.GONE
        }

        override fun showList(updateReviewList: UpdateReviewList) = Unit
    }

    class Error(
        private val exception: Exception,
    ) : ReviewsUiState {

        override fun show(binding: FragmentReviewListBinding) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.errorTextView.text = exception.message
            binding.errorLayout.visibility = View.VISIBLE
        }

        override fun showList(updateReviewList: UpdateReviewList) = Unit
    }

    class Reviews(
        private val list: List<Review>,
    ) : ReviewsUiState {

        override fun show(binding: FragmentReviewListBinding) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.errorLayout.visibility = View.GONE
        }
        override fun showList(updateReviewList: UpdateReviewList) {
            updateReviewList.update(list)
        }
    }
}