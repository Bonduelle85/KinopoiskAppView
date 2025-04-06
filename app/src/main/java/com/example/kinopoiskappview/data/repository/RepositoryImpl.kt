package com.example.kinopoiskappview.data.repository

import com.example.kinopoiskappview.data.Mapper
import com.example.kinopoiskappview.data.database.MoviesDao
import com.example.kinopoiskappview.data.network.ApiService
import com.example.kinopoiskappview.domain.Repository
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Trailer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val apiService: ApiService,
    private val mapper: Mapper
) : Repository {

    private var page = 1
    private val cachedMovies = mutableListOf<Movie>()

    override suspend fun loadMovies(): Flow<List<Movie>> = flow {
        val rootResponseDto = apiService.loadMovies(page++)
        val movies = mapper.mapDtoModelListToDomainList(rootResponseDto.movies)
        cachedMovies.addAll(movies)
        emit(cachedMovies.toList())
    }.flowOn(Dispatchers.IO)

    override suspend fun loadTrailers(id: Long): Flow<List<Trailer>> = flow {
        val trailersRootResponseDto = apiService.loadTrailers(id)
        val trailers =
            mapper.mapTrailerDtoListToTrailerDomainList(trailersRootResponseDto.videosDto.trailerDtos)
        emit(trailers)
    }.flowOn(Dispatchers.IO)

}