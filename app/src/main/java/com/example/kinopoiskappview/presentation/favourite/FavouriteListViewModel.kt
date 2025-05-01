package com.example.kinopoiskappview.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.domain.GetFavouritesUseCase
import com.example.kinopoiskappview.domain.RemoveMovieUseCase
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteListViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val removeMovieUseCase: RemoveMovieUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavouriteMoviesUiState>(FavouriteMoviesUiState.Initial)
    val uiState get() = _uiState.asStateFlow()

    init {
        getFavouriteMovies()
    }

    fun removeMovie(movieId: Long) {
        viewModelScope.launch {
            removeMovieUseCase.invoke(movieId)
        }
    }

    fun getFavouriteMovies() {
        viewModelScope.launch {
            getFavouritesUseCase.invoke().collect { favouriteMovies ->
                _uiState.update { favouriteMovies.toUiState() }
            }
        }
    }

    private fun List<Movie>.toUiState(): FavouriteMoviesUiState {
        return if (this.isEmpty()) {
            FavouriteMoviesUiState.EmptyWithMessage
        } else
            FavouriteMoviesUiState.Movies(this)
    }
}