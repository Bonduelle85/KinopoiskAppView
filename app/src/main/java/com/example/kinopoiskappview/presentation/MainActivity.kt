package com.example.kinopoiskappview.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kinopoiskappview.App
import com.example.kinopoiskappview.R
import com.example.kinopoiskappview.databinding.ActivityMainBinding
import com.example.kinopoiskappview.navigation.Navigation
import com.example.kinopoiskappview.navigation.Screen
import com.example.kinopoiskappview.presentation.movielist.MovieListFragment

class MainActivity : AppCompatActivity(), Navigation {

    private lateinit var binding: ActivityMainBinding

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, MovieListFragment.newInstance())
                .commit()
        }
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.fragmentContainer, supportFragmentManager)
    }
}