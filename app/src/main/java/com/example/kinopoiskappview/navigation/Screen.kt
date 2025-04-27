package com.example.kinopoiskappview.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(containerId: Int, fragmentManager: FragmentManager)

    abstract class ReplaceWithBackstack : Screen {

        abstract fun fragment(): Fragment

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            val fragment = fragment()
            fragmentManager.beginTransaction()
                .replace(containerId, fragment())
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
        }
    }
}