package com.example.kinopoiskappview.presentation.reviewlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.di.MovieQualifier
import com.example.kinopoiskappview.domain.LoadReviewsUseCase
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Review
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewListViewModel @Inject constructor(
    private val loadReviewsUseCase: LoadReviewsUseCase,
    @MovieQualifier private val movie: Movie
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Review>>(emptyList())
    val uiState: StateFlow<List<Review>> = _uiState

    fun loadReviews() {
        viewModelScope.launch {
            loadReviewsUseCase.invoke(movie.id).collect { reviews ->
                _uiState.value = reviews
            }
        }
    }
}