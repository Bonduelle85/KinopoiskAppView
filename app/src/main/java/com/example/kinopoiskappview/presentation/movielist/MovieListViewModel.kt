package com.example.kinopoiskappview.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.domain.GetMovieListUseCase
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MovieListViewModel(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    val uiState: StateFlow<List<Movie>> = getMovieListUseCase.invoke()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )
}