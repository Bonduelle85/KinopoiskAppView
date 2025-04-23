package com.example.kinopoiskappview.domain

import javax.inject.Inject

class RemoveMovieUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun  invoke(movieId: Long) {
        repository.remove(movieId)
    }
}