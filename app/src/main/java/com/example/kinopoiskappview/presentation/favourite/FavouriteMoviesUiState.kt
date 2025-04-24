package com.example.kinopoiskappview.presentation.favourite

import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.favourite.adapter.UpdateFavouriteList

sealed interface FavouriteMoviesUiState {

    fun showList(updateFavouriteList: UpdateFavouriteList)

    class FavouriteMovies(
        private val list: List<Movie>,
    ) : FavouriteMoviesUiState {

        override fun showList(updateFavouriteList: UpdateFavouriteList) {
            updateFavouriteList.update(list)
        }
    }

    data object Empty : FavouriteMoviesUiState {

        override fun showList(updateFavouriteList: UpdateFavouriteList) = Unit
    }
}