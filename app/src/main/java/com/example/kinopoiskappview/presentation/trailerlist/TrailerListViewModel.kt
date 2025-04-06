package com.example.kinopoiskappview.presentation.trailerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.di.MovieQualifier
import com.example.kinopoiskappview.domain.LoadTrailersUseCase
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Trailer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrailerListViewModel @Inject constructor(
    private val loadTrailersUseCase: LoadTrailersUseCase,
    @MovieQualifier private val movie: Movie
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Trailer>>(emptyList())
    val uiState: StateFlow<List<Trailer>> = _uiState

    fun loadTrailers() {
        viewModelScope.launch {
            loadTrailersUseCase.invoke(movie.id).collect { trailers ->
                _uiState.value = trailers
            }
        }
    }
}