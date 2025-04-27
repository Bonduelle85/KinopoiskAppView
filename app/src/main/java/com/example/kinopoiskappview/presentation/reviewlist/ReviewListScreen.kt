package com.example.kinopoiskappview.presentation.reviewlist

import androidx.fragment.app.Fragment
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.navigation.Screen

class ReviewListScreen(private val movie: Movie) : Screen.ReplaceWithBackstack() {

    override fun fragment(): Fragment = ReviewListFragment.newInstance(movie)
}