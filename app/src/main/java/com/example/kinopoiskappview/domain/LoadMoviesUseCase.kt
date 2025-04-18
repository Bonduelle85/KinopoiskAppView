package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun  invoke(): Flow<Result<List<Movie>>> = repository.loadMovies()
}