package com.example.kinopoiskappview.di

import androidx.lifecycle.ViewModel
import com.example.kinopoiskappview.presentation.movielist.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    @Binds
    fun bindShopListViewModel(movieListViewModel: MovieListViewModel): ViewModel
}