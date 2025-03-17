package com.example.kinopoiskappview.domain

import com.example.kinopoiskappview.data.database.MovieDbModel
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getMovieList(): Flow<List<Movie>>

    fun insertMovieList(movieList: List<MovieDbModel>)

    fun loadMovies()
}