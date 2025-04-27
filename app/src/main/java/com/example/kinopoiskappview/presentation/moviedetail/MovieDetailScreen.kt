package com.example.kinopoiskappview.presentation.moviedetail

import androidx.fragment.app.Fragment
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.navigation.Screen

class MovieDetailScreen(private val movie: Movie) : Screen.ReplaceWithBackstack() {

    override fun fragment(): Fragment = MovieDetailFragment.newInstance(movie)
}