package com.example.kinopoiskappview.data

import com.example.kinopoiskappview.data.database.MovieDbModel
import com.example.kinopoiskappview.data.network.model.MovieDto
import com.example.kinopoiskappview.domain.model.Movie
import javax.inject.Inject
import kotlin.math.round

class Mapper @Inject constructor() {

    fun mapToMovieDbModelList(movieDtoList: List<MovieDto>) = movieDtoList.map {
        mapMovieDtoToMovieDbModel(it)
    }

    fun mapDbModelListToDomainList(dbModelList: List<MovieDbModel>) = dbModelList.map {
        mapDbModelToDomain(it)
    }

    fun mapDtoModelListToDomainList(dtoModelList: List<MovieDto>) = dtoModelList.map {
        mapDtoModelToDomain(it)
    }

    private fun mapDtoModelToDomain(movieDto: MovieDto) = Movie(
        id = movieDto.id,
        name = movieDto.name,
        year = movieDto.year,
        description = movieDto.description,
        kpRating = movieDto.ratingDto.kp.round(1),
        imdbRating = movieDto.ratingDto.imdb.round(1),
        posterUrl = movieDto.posterDto.url,
        genres = movieDto.genreDtoList.map { it.genre }
    )

    private fun mapDbModelToDomain(movieDbModel: MovieDbModel) = Movie(
        id = movieDbModel.id,
        name = movieDbModel.name,
        year = movieDbModel.year,
        description = movieDbModel.description,
        kpRating = movieDbModel.kpRating,
        imdbRating = movieDbModel.imdbRating,
        posterUrl = movieDbModel.posterUrl,
        genres = movieDbModel.genres
    )

    private fun mapMovieDtoToMovieDbModel(movieDto: MovieDto) = MovieDbModel(
        id = movieDto.id,
        name = movieDto.name,
        year = movieDto.year,
        description = movieDto.description,
        kpRating = movieDto.ratingDto.kp.round(1),
        imdbRating = movieDto.ratingDto.imdb.round(1),
        posterUrl = movieDto.posterDto.url,
        genres = movieDto.genreDtoList.map { it.genre }
    )

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
}