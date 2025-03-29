package com.example.kinopoiskappview.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.domain.GetMovieListUseCase
import com.example.kinopoiskappview.domain.LoadMoviesUseCase
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val loadMoviesUseCase: LoadMoviesUseCase
) : ViewModel() {

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            loadMoviesUseCase.invoke()
        }
    }

    val uiState: StateFlow<List<Movie>> = getMovieListUseCase.invoke()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )
}