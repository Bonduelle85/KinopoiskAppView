package com.example.kinopoiskappview.presentation.trailerlist

import androidx.fragment.app.Fragment
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.navigation.Screen

class TrailerListScreen(private val movie: Movie) : Screen.ReplaceWithBackstack() {

    override fun fragment(): Fragment = TrailerListFragment.newInstance(movie)
}