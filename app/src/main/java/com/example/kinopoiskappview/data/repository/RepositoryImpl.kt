package com.example.kinopoiskappview.data.repository

import com.example.kinopoiskappview.data.Mapper
import com.example.kinopoiskappview.data.database.MoviesDao
import com.example.kinopoiskappview.data.network.ApiService
import com.example.kinopoiskappview.data.network.model.RootResponseDto
import com.example.kinopoiskappview.domain.Repository
import com.example.kinopoiskappview.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random

class RepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val apiService: ApiService,
    private val mapper: Mapper
) : Repository {

    override suspend fun loadMovies(): Flow<List<Movie>> = flow {
        val rootResponseDto = apiService.loadMovies()
        val movies = mapper.mapDtoModelListToDomainList(rootResponseDto.movies)
        emit(movies)
    }.flowOn(Dispatchers.IO)

    override suspend fun insertMovieList() {
        val rootResponseDto: RootResponseDto = apiService.loadMovies()
        val movieDtoList = rootResponseDto.movies
        moviesDao.insertMovieList(mapper.mapToMovieDbModelList(movieDtoList))
    }
}