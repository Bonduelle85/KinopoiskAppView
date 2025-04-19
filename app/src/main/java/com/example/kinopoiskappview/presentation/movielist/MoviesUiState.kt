package com.example.kinopoiskappview.presentation.movielist

import android.view.View
import com.example.kinopoiskappview.databinding.FragmentMovieListBinding
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.movielist.adapter.UpdateMovieList

sealed interface MoviesUiState {

    fun show(binding: FragmentMovieListBinding)
    fun showList(updateMovieList: UpdateMovieList)

    object Loading : MoviesUiState {

        override fun show(binding: FragmentMovieListBinding) {
            binding.progressBar.visibility = View.VISIBLE
            binding.errorInclude.errorLayout.visibility = View.GONE
        }

        override fun showList(updateMovieList: UpdateMovieList) = Unit
    }

    class Error(
        private val message: String,
    ) : MoviesUiState {

        override fun show(binding: FragmentMovieListBinding) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.errorInclude.errorTextView.text = message
            binding.errorInclude.errorLayout.visibility = View.VISIBLE
        }

        override fun showList(updateMovieList: UpdateMovieList) = Unit
    }

    class Movies(
        private val list: List<Movie>,
    ) : MoviesUiState {

        override fun show(binding: FragmentMovieListBinding) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.errorInclude.errorLayout.visibility = View.GONE
        }

        override fun showList(updateMovieList: UpdateMovieList) {
            updateMovieList.update(list)
        }
    }
}