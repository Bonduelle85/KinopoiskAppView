package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Result
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Review
import com.example.kinopoiskappview.domain.model.Trailer
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun loadMovies(): Flow<List<Movie>>
    suspend fun loadTrailers(id: Long): Flow<Result<List<Trailer>>>
    suspend fun loadReviews(id: Long): Flow<Result<List<Review>>>
}