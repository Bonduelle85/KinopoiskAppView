package com.example.kinopoiskappview.presentation.reviewlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.data.repository.Result
import com.example.kinopoiskappview.di.MovieQualifier
import com.example.kinopoiskappview.domain.LoadReviewsUseCase
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Review
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewListViewModel @Inject constructor(
    private val loadReviewsUseCase: LoadReviewsUseCase,
    @MovieQualifier private val movie: Movie
) : ViewModel() {

    private val _uiState = MutableStateFlow<ReviewsUiState>(ReviewsUiState.Loading)
    val uiState: StateFlow<ReviewsUiState> = _uiState

    fun loadReviews() {
        viewModelScope.launch {
            loadReviewsUseCase.invoke(movie.id).collect { result ->
                _uiState.value = ReviewsUiState.Loading
                delay(300)
                _uiState.value = mapResultToUiState(result)
            }
        }
    }

    private fun mapResultToUiState(result: Result): ReviewsUiState {
        return when (result) {
            is Result.Success -> ReviewsUiState.Reviews(result.data)
            is Result.Error -> ReviewsUiState.Error(result.error)
        }
    }
}
