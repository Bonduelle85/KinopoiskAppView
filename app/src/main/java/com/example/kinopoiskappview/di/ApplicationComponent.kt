package com.example.kinopoiskappview.di

import android.app.Application
import com.example.kinopoiskappview.App
import com.example.kinopoiskappview.presentation.MainActivity
import com.example.kinopoiskappview.presentation.movielist.MovieListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: MovieListFragment)
    fun inject(application: App)

    fun trailerListComponentComponentFactory(): TrailerListComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}