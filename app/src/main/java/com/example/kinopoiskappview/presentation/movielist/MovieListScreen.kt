package com.example.kinopoiskappview.presentation.movielist

import androidx.fragment.app.Fragment
import com.example.kinopoiskappview.navigation.Screen

class MovieListScreen :Screen.ReplaceWithBackstack() {

    override fun fragment(): Fragment  = MovieListFragment.newInstance()
}