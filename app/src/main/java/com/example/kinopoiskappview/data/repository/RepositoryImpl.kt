package com.example.kinopoiskappview.data.repository

import com.example.kinopoiskappview.data.database.MovieDbModel
import com.example.kinopoiskappview.domain.Repository
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(

) : Repository {

    override fun getMovieList(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun insertMovieList(movieList: List<MovieDbModel>) {
        TODO("Not yet implemented")
    }

    override fun loadMovies() {
        TODO("Not yet implemented")
    }
}