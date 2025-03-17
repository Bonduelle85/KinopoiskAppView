package com.example.kinopoiskappview.domain.model

data class Movie(
    val id: Long,
    val name: String,
    val year: String,
    val description: String,
    val kpRating: Double,
    val imdbRating: Double,
    val posterUrl: String,
    val genres: List<String>,
)