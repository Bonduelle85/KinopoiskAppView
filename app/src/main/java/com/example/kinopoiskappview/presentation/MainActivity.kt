package com.example.kinopoiskappview.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kinopoiskappview.R
import com.example.kinopoiskappview.databinding.ActivityMainBinding
import com.example.kinopoiskappview.presentation.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
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
}