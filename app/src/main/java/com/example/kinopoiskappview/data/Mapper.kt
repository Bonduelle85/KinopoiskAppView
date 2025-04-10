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

    fun mapTrailerDtoListToTrailerDomainList(trailerDtoList: List<TrailerDto>) = trailerDtoList.map {
        mapTrailerDtoToDomain(it)
    }

    fun mapToMovieDbModelList(movieDtoList: List<MovieDto>) = movieDtoList.map {
        mapMovieDtoToMovieDbModel(it)
    }

    fun mapDbModelListToDomainList(dbModelList: List<MovieDbModel>) = dbModelList.map {
        mapDbModelToDomain(it)
    }

    fun mapDtoModelListToDomainList(dtoModelList: List<MovieDto>) = dtoModelList.map {
        mapDtoModelToDomain(it)
    }

    private fun mapTrailerDtoToDomain(trailerDto: TrailerDto) = Trailer(
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

    private fun mapDtoModelToDomain(movieDto: MovieDto) = Movie(
        id = movieDto.id,
        name = movieDto.name,
        year = movieDto.year,
        description = movieDto.description,
        kpRating = movieDto.ratingDto.kp.round(1),
        posterUrl = movieDto.posterDto.url,
        genres = movieDto.genreDtoList.map { it.genre }
    )

    private fun mapDbModelToDomain(movieDbModel: MovieDbModel) = Movie(
        id = movieDbModel.id,
        name = movieDbModel.name,
        year = movieDbModel.year,
        description = movieDbModel.description,
        kpRating = movieDbModel.kpRating,
        posterUrl = movieDbModel.posterUrl,
        genres = movieDbModel.genres
    )

    private fun mapMovieDtoToMovieDbModel(movieDto: MovieDto) = MovieDbModel(
        id = movieDto.id,
        name = movieDto.name,
        year = movieDto.year,
        description = movieDto.description,
        kpRating = movieDto.ratingDto.kp.round(1),
        posterUrl = movieDto.posterDto.url,
        genres = movieDto.genreDtoList.map { it.genre }
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