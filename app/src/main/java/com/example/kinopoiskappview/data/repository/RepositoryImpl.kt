package com.example.kinopoiskappview.data.repository

import com.example.kinopoiskappview.data.Mapper
import com.example.kinopoiskappview.data.network.ApiService
import com.example.kinopoiskappview.domain.Repository
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Result
import com.example.kinopoiskappview.domain.model.Review
import com.example.kinopoiskappview.domain.model.Trailer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper
) : Repository {

    private var page = 1
    private val cachedMovies = mutableListOf<Movie>()

    override suspend fun loadMovies(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        delay(200)
        try {
            val moviesResponse = apiService.loadMovies(page)
            val movies = moviesResponse.movies.map { mapper.mapDtoToDomain(it) }
            cachedMovies.addAll(movies)
            emit(Result.Success(cachedMovies.toList()))
            page++
        } catch (e: Exception) {
            emit(Result.Error(classifyError(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loadTrailers(id: Long): Flow<Result<List<Trailer>>> = flow {
        emit(Result.Loading)
        delay(200)
        try {
            val trailersResponse = apiService.loadTrailers(id)
            val trailers = trailersResponse.videosDto.trailerDtos.map { mapper.mapDtoToDomain(it) }
            emit(Result.Success(trailers))
        } catch (e: Exception) {
            emit(Result.Error(classifyError(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loadReviews(id: Long): Flow<Result<List<Review>>> = flow {
        emit(Result.Loading)
        delay(200)
        try {
            val reviewResponse = apiService.loadReviews(id).reviews
            val reviews = reviewResponse.map { mapper.mapDtoToDomain(it) }
            emit(Result.Success(reviews))
        } catch (e: Exception) {
            emit(Result.Error(classifyError(e)))
        }
    }.flowOn(Dispatchers.IO)

    private fun classifyError(e: Exception): String = when (e) {
        is SocketTimeoutException -> "Connection timeout"
        is UnknownHostException -> "No internet connection"
        is IOException -> "Network error"
        else -> "Unknown error: ${e.message}"
    }
}