package com.example.kinopoiskappview.domain

import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun  invoke() = repository.loadMovies()
}