package com.example.kinopoiskappview.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskappview.domain.GetFavouritesUseCase
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteListViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavouriteMoviesUiState>(FavouriteMoviesUiState.Empty)
    val uiState: StateFlow<FavouriteMoviesUiState> = _uiState

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        viewModelScope.launch {
            getFavouritesUseCase.invoke().collect { favouriteMovies ->
                _uiState.value = favouriteMovies.toUiState()
            }
        }
    }

    private fun List<Movie>.toUiState(): FavouriteMoviesUiState = FavouriteMoviesUiState.FavouriteMovies(this)
}