package com.example.kinopoiskappview.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.domain.LoadMoviesUseCase
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Movie>>(emptyList())
    val uiState: StateFlow<List<Movie>> = _uiState

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            loadMoviesUseCase.invoke().collect { movies ->
                _uiState.value = movies
            }
        }
    }
}

