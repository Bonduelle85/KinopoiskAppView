package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getMovieList(): Flow<List<Movie>>

    suspend fun insertMovieList()
}