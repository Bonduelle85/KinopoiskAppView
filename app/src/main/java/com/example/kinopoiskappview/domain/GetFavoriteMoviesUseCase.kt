package com.example.kinopoiskappview.domain


import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: Repository
) {

//     operator fun invoke(): Flow<List<Movie>> = repository
}