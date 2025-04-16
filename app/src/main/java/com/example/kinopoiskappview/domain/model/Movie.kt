package com.example.kinopoiskappview.domain.model

import java.io.Serializable

data class Movie(
    val id: Long,
    val name: String,
    val year: String,
    val description: String,
    val kpRating: Double,
    val posterUrl: String,
    val genres: List<String>,
) : Serializable