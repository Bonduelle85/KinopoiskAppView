package com.example.kinopoiskappview.data.repository

import com.example.kinopoiskappview.data.Mapper
import com.example.kinopoiskappview.data.database.MoviesDao
import com.example.kinopoiskappview.data.network.ApiService
import com.example.kinopoiskappview.domain.Repository
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Review
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
        val moviesResponse = apiService.loadMovies(page++)
        val movies = mapper.mapDtoModelListToDomainList(moviesResponse.movies)
        cachedMovies.addAll(movies)
        emit(cachedMovies.toList())
    }.flowOn(Dispatchers.IO)

    override suspend fun loadTrailers(id: Long): Flow<List<Trailer>> = flow {
        val trailersResponse = apiService.loadTrailers(id)
        val trailers =
            mapper.mapTrailerDtoListToTrailerDomainList(trailersResponse.videosDto.trailerDtos)
        emit(trailers)
    }.flowOn(Dispatchers.IO)

    override suspend fun loadReviews(id: Long): Flow<List<Review>> = flow {
        val reviewResponse = apiService.loadReviews(id).reviews
        val reviews = reviewResponse.map { mapper.mapDtoToDomain(it) }
        emit(reviews)
    }.flowOn(Dispatchers.IO)
}