package com.example.kinopoiskappview.data

import com.example.kinopoiskappview.data.database.MovieDbModel
import com.example.kinopoiskappview.data.network.model.MovieDto
import com.example.kinopoiskappview.domain.model.Movie

class Mapper {

    fun mapDbModelToDomain(movieDbModel: MovieDbModel) = Movie(
        id = movieDbModel.id,
        name = movieDbModel.name,
        year = movieDbModel.year,
        description = movieDbModel.description,
        kpRating = movieDbModel.kpRating,
        imdbRating = movieDbModel.imdbRating,
        posterUrl = movieDbModel.posterUrl,
        genres = movieDbModel.genres
    )

    fun mapMovieDtoToMovieDbModel(movieDto: MovieDto) = MovieDbModel(
        id = movieDto.id,
        name = movieDto.name,
        year = movieDto.year,
        description = movieDto.description,
        kpRating = movieDto.ratingDto.kp,
        imdbRating = movieDto.ratingDto.imdb,
        posterUrl = movieDto.posterDto.url,
        genres = movieDto.genreDtoList.map { it.genre }
    )

    fun mapToMovieDbModelList(movieDtoList: List<MovieDto>) = movieDtoList.map {
        mapMovieDtoToMovieDbModel(it)
    }

    fun mapDbModelListToDomainList(dbModelList: List<MovieDbModel>) = dbModelList.map {
        mapDbModelToDomain(it)
    }
}