package com.example.kinopoiskappview.data

import com.example.kinopoiskappview.data.database.MovieDbModel
import com.example.kinopoiskappview.data.network.model.MovieDto
import com.example.kinopoiskappview.domain.model.Movie


fun MovieDbModel.mapToDomain() = Movie(
    id = id,
    name = name,
    year = year,
    description = description,
    kpRating = kpRating,
    imdbRating = imdbRating,
    posterUrl = posterUrl,
    genres = genres
)

fun MovieDto.mapToMovieDbModel() = MovieDbModel(
    id = id,
    name = name,
    year = year,
    description = description,
    kpRating = this.ratingDto.kp,
    imdbRating = this.ratingDto.imdb,
    posterUrl = posterDto.url,
    genres = genreDtoList.map { it.genre }
)