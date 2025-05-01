package com.example.kinopoiskappview.presentation.reviewlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.di.MovieQualifier
import com.example.kinopoiskappview.domain.LoadReviewsUseCase
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewListViewModel @Inject constructor(
    private val loadReviewsUseCase: LoadReviewsUseCase,
    @MovieQualifier private val movie: Movie
) : ViewModel() {

    private val _uiState = MutableStateFlow<ReviewsUiState>(ReviewsUiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun loadReviews() {
        viewModelScope.launch {
            loadReviewsUseCase.invoke(movie.id).collect { result ->
                _uiState.value = result.toUiState {
                    ReviewsUiState.Reviews(it)
                }
            }
        }
    }

    private fun <T> Result<T>.toUiState(
        successTransform: (T) -> ReviewsUiState
    ): ReviewsUiState = when (this) {
        is Result.Loading -> ReviewsUiState.Loading
        is Result.Success -> successTransform(data)
        is Result.Error -> ReviewsUiState.Error(message)
    }
}
