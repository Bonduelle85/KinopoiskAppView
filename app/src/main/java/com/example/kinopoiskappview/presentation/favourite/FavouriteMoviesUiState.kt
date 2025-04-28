package com.example.kinopoiskappview.presentation.favourite

import android.view.View
import com.example.kinopoiskappview.databinding.FragmentFavouriteListBinding
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.favourite.adapter.UpdateFavouriteList

sealed interface FavouriteMoviesUiState {

    fun showList(updateFavouriteList: UpdateFavouriteList)
    fun showUi(binding: FragmentFavouriteListBinding)

    data object Initial : FavouriteMoviesUiState {
        override fun showList(updateFavouriteList: UpdateFavouriteList) = Unit
        override fun showUi(binding: FragmentFavouriteListBinding) = Unit
    }

    data object EmptyWithMessage : FavouriteMoviesUiState {
        override fun showList(updateFavouriteList: UpdateFavouriteList) = Unit
        override fun showUi(binding: FragmentFavouriteListBinding) {
            binding.emptyListTextView.visibility = View.VISIBLE
        }
    }

    class Movies(
        private val list: List<Movie>,
    ) : FavouriteMoviesUiState {
        override fun showList(updateFavouriteList: UpdateFavouriteList) {
            updateFavouriteList.update(list)
        }
        override fun showUi(binding: FragmentFavouriteListBinding) {
            binding.emptyListTextView.visibility = View.INVISIBLE
        }
    }
}