package com.example.kinopoiskappview.presentation.trailerlist

import android.view.View
import com.example.kinopoiskappview.databinding.FragmentTrailerListBinding
import com.example.kinopoiskappview.domain.model.Trailer
import com.example.kinopoiskappview.presentation.trailerlist.adapter.UpdateTrailerList

sealed interface TrailersUiState {

    fun show(binding: FragmentTrailerListBinding)
    fun showList(updateTrailerList: UpdateTrailerList)

    object Loading : TrailersUiState {

        override fun show(binding: FragmentTrailerListBinding) {
            binding.progressBar.visibility = View.VISIBLE
            binding.errorInclude.errorLayout.visibility = View.GONE
        }

        override fun showList(updateTrailerList: UpdateTrailerList) = Unit
    }

    class Error(
        private val exception: Exception,
    ) : TrailersUiState {

        override fun show(binding: FragmentTrailerListBinding) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.errorInclude.errorTextView.text = exception.message
            binding.errorInclude.errorLayout.visibility = View.VISIBLE
        }

        override fun showList(updateTrailerList: UpdateTrailerList) = Unit
    }

    class Trailers(
        private val list: List<Trailer>,
    ) : TrailersUiState {

        override fun show(binding: FragmentTrailerListBinding) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.errorInclude.errorLayout.visibility = View.GONE
        }

        override fun showList(updateTrailerList: UpdateTrailerList) {
            updateTrailerList.update(list)
        }
    }
}