package com.example.kinopoiskappview.presentation.trailerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.di.MovieQualifier
import com.example.kinopoiskappview.domain.LoadTrailersUseCase
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrailerListViewModel @Inject constructor(
    private val loadTrailersUseCase: LoadTrailersUseCase,
    @MovieQualifier private val movie: Movie
) : ViewModel() {

    private val _uiState = MutableStateFlow<TrailersUiState>(TrailersUiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun loadTrailers() {
        viewModelScope.launch {
            loadTrailersUseCase.invoke(movie.id).collect { result ->
                _uiState.value = result.toUiState {
                    TrailersUiState.Trailers(it)
                }
            }
        }
    }

    private fun <T> Result<T>.toUiState(
        successTransform: (T) -> TrailersUiState
    ): TrailersUiState = when (this) {
        is Result.Loading -> TrailersUiState.Loading
        is Result.Success -> successTransform(data)
        is Result.Error -> TrailersUiState.Error(message)
    }
}