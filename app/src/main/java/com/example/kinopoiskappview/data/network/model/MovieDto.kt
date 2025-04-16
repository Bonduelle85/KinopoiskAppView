package com.example.kinopoiskappview.data.network.model

import com.google.gson.annotations.SerializedName


data class MovieDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("year") val year: String,
    @SerializedName("description") val description: String,
    @SerializedName("rating") val ratingDto: RatingDto,
    @SerializedName("poster") val posterDto: PosterDto,
    @SerializedName("genres") val genreDtoList: List<GenreDto>,
)