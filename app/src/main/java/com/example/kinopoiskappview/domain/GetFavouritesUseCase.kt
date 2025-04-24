package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun  invoke(): Flow<List<Movie>> = repository.getFavouriteMovies()
}