package com.example.kinopoiskappview.presentation.moviedetail

import com.example.kinopoiskappview.databinding.FragmentMovieDetailBinding

interface CheckboxUiState {

    fun show(binding: FragmentMovieDetailBinding)

    object Added : CheckboxUiState {

        override fun show(binding: FragmentMovieDetailBinding) {
            binding.favouriteImageView.setImageResource(android.R.drawable.checkbox_on_background)
        }
    }

    object NotAdded : CheckboxUiState {

        override fun show(binding: FragmentMovieDetailBinding) {
            binding.favouriteImageView.setImageResource(android.R.drawable.checkbox_off_background)
        }
    }
}