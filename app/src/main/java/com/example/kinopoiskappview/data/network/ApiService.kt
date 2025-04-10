package com.example.kinopoiskappview.data.network

import com.example.kinopoiskappview.data.network.model.ReviewsResponse
import com.example.kinopoiskappview.data.network.model.MoviesResponse
import com.example.kinopoiskappview.data.network.model.TrailersResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //  @GET("v1.4/movie?token=SK68PA6-WFDM50Q-N5EV2D5-17FVV24&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&page=1&limit=40")
    @GET("v1.4/movie")
    @Headers("X-API-KEY:${TOKEN}")
    suspend fun loadMovies(
        @Query("page") page: Number,
        @Query("field") field: String = "rating.kp",
        @Query("search") search: Number = 7 - 10,
        @Query("sortField") sortField: String = "votes.kp",
        @Query("sortType") sortType: Number = -1,
        @Query("limit") limit: Number = 30,
    ): MoviesResponse

    //  @GET("v1.4/movie/535341?token=SK68PA6-WFDM50Q-N5EV2D5-17FVV24")
    @GET("v1.4/movie/{id}")
    @Headers("X-API-KEY:${TOKEN}")
    suspend fun loadTrailers(
        @Path("id") movieId: Long,
): TrailersResponse

    //  @GET("v1.4/review?token=SK68PA6-WFDM50Q-N5EV2D5-17FVV24&movieId=535341&page=1&limit=40")
    @GET("v1.4/review")
    @Headers("X-API-KEY:${TOKEN}")
    suspend fun loadReviews(
        @Query("movieId") id: Long,
        @Query("page") page: Number = 1,
        @Query("limit") limit: Number = 40,
    ): ReviewsResponse

    companion object {
        private const val TOKEN =  "SK68PA6-WFDM50Q-N5EV2D5-17FVV24"
    }

}