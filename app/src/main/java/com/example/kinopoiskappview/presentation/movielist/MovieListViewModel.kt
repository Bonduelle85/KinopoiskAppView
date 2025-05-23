package com.example.kinopoiskappview.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.domain.LoadMoviesUseCase
import com.example.kinopoiskappview.domain.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            loadMoviesUseCase.invoke().collect { result ->
                _uiState.value = result.toUiState {
                    MoviesUiState.Movies(it)
                }
            }
        }
    }

    private fun <T> Result<T>.toUiState(
        successTransform: (T) -> MoviesUiState
    ): MoviesUiState = when (this) {
        is Result.Loading -> MoviesUiState.Loading
        is Result.Success -> successTransform(data)
        is Result.Error -> MoviesUiState.Error(message)
    }
}

