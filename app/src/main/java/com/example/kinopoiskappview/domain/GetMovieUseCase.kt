package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun  invoke(movieId: Long): Flow<Movie?> = repository.getMovie(movieId)
}