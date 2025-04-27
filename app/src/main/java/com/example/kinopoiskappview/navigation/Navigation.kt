package com.example.kinopoiskappview.navigation

import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.favourite.FavouriteListScreen
import com.example.kinopoiskappview.presentation.moviedetail.MovieDetailScreen
import com.example.kinopoiskappview.presentation.reviewlist.ReviewListScreen
import com.example.kinopoiskappview.presentation.trailerlist.TrailerListScreen

interface Navigation : MovieListNavigation, FavouriteListNavigation, MovieDetailNavigation,
    ReviewListNavigation, TrailerListNavigation {

    fun navigate(screen: Screen)

    override fun toMovieDetailScreen(movie: Movie) {
        navigate(MovieDetailScreen(movie))
    }

    override fun toFavouriteListScreen() {
        navigate(FavouriteListScreen())
    }

    override fun toReviewListScreen(movie: Movie) {
        navigate(ReviewListScreen(movie))
    }

    override fun toTrailerListScreen(movie: Movie) {
        navigate(TrailerListScreen(movie))
    }
}

interface MovieListNavigation {
    fun toMovieDetailScreen(movie: Movie)
    fun toFavouriteListScreen()
}

interface MovieDetailNavigation {
    fun toReviewListScreen(movie: Movie)
    fun toTrailerListScreen(movie: Movie)
}

interface FavouriteListNavigation {
    fun toMovieDetailScreen(movie: Movie)
}

interface ReviewListNavigation {
    fun toFavouriteListScreen()
}

interface TrailerListNavigation {
    fun toFavouriteListScreen()
}