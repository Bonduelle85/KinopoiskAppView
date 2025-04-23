package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Movie
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun  invoke(movie: Movie) {
        repository.addMovie(movie)
    }
}