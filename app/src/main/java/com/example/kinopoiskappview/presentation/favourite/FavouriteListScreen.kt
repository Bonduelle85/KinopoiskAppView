package com.example.kinopoiskappview.presentation.favourite

import androidx.fragment.app.Fragment
import com.example.kinopoiskappview.navigation.Screen

class FavouriteListScreen : Screen.ReplaceWithBackstack() {

    override fun fragment(): Fragment = FavouriteListFragment.newInstance()
}