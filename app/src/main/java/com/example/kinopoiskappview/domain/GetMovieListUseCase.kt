package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase(
    private val repository: Repository
) {

     operator fun invoke(): Flow<List<Movie>> = repository.getMovieList()
}