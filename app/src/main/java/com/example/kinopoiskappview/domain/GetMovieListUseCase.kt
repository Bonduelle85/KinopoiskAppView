package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: Repository
) {

//     operator fun invoke(): Flow<List<Movie>> = repository
}