package com.example.kinopoiskappview.data

import com.example.kinopoiskappview.data.database.model.MovieDbModel
import com.example.kinopoiskappview.data.network.model.MovieDto
import com.example.kinopoiskappview.data.network.model.ReviewDto
import com.example.kinopoiskappview.data.network.model.TrailerDto
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.domain.model.Review
import com.example.kinopoiskappview.domain.model.Trailer
import javax.inject.Inject
import kotlin.math.round

class Mapper @Inject constructor() {

    fun mapDtoToDomain(trailerDto: TrailerDto) = Trailer(
        trailerName = trailerDto.trailerName,
        url = trailerDto.url
    )

    fun mapDtoToDomain(reviewDto: ReviewDto) = Review(
        author = reviewDto.author,
        title = reviewDto.title,
        type = reviewDto.type.toEnumType(),
        review = reviewDto.review,
        likes = reviewDto.likes,
        dislikes = reviewDto.dislikes,
        createdAt = reviewDto.createdAt.substringDate()
    )

    fun mapDtoToDomain(movieDto: MovieDto) = Movie(
        id = movieDto.id,
        name = movieDto.name,
        year = movieDto.year,
        description = movieDto.description,
        kpRating = movieDto.ratingDto.kp.round(1),
        posterUrl = movieDto.posterDto.url,
        genres = movieDto.genreDtoList.map { it.genre }
    )

    fun mapDbToDomain(movieDbModel: MovieDbModel) = Movie(
        id = movieDbModel.id,
        name = movieDbModel.name,
        year = movieDbModel.year,
        description = movieDbModel.description,
        kpRating = movieDbModel.kpRating,
        posterUrl = movieDbModel.posterUrl,
        genres = movieDbModel.genres
    )

    fun mapDomainToDb(movie: Movie) = MovieDbModel(
        id = movie.id,
        name = movie.name,
        year = movie.year,
        description = movie.description,
        kpRating = movie.kpRating,
        posterUrl = movie.posterUrl,
        genres = movie.genres
    )

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    private fun String.toEnumType(): ReviewType {
        return when(this) {
            "Позитивный" -> ReviewType.POSITIVE
            "Негативный" -> ReviewType.NEGATIVE
            "Нейтральный" -> ReviewType.NEUTRAL
            else -> throw RuntimeException("Unknown review type: $this")
        }
    }

    private fun String?.substringDate() = this?.substring(0, 10) ?: ""
}

enum class ReviewType { POSITIVE, NEGATIVE, NEUTRAL }