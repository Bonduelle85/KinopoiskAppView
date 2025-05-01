package com.example.kinopoiskappview.presentation.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.di.MovieQualifier
import com.example.kinopoiskappview.domain.AddMovieUseCase
import com.example.kinopoiskappview.domain.GetMovieUseCase
import com.example.kinopoiskappview.domain.RemoveMovieUseCase
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val addMovieUseCase: AddMovieUseCase,
    private val removeMovieUseCase: RemoveMovieUseCase,
    @MovieQualifier private val movie: Movie
) : ViewModel() {

    private val _uiState = MutableStateFlow<CheckboxUiState>(CheckboxUiState.NotAdded)
    val uiState get() = _uiState.asStateFlow()

    fun getMovie() {
        viewModelScope.launch {
            getMovieUseCase.invoke(movie.id).collect { movie: Movie? ->
                _uiState.update { movie.toUiState() }
            }
        }
    }

    fun addMovie() {
        viewModelScope.launch {
            addMovieUseCase.invoke(movie)
        }
    }

    fun removeMovie() {
        viewModelScope.launch {
            removeMovieUseCase.invoke(movie.id)
        }
    }

    private fun Movie?.toUiState(): CheckboxUiState =
        if (this == null) CheckboxUiState.NotAdded else CheckboxUiState.Added
}