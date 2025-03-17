package com.example.kinopoiskappview.data.network

import com.example.kinopoiskappview.data.network.model.RootResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1.4/movie")
//    @GET("v1.4/movie?token=SK68PA6-WFDM50Q-N5EV2D5-17FVV24&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&page=1&limit=40")

    suspend fun loadMovies(
        @Query("token") token: String = "SK68PA6-WFDM50Q-N5EV2D5-17FVV24",
        @Query("field") field: String = "rating.kp",
        @Query("search") search: Number = 7-10,
        @Query("sortField") sortField: String = "votes.kp",
        @Query("sortType") sortType: Number = -1,
        @Query("page") page: Number = 1,
        @Query("limit") limit: Number = 40,
        ): RootResponseDto
}