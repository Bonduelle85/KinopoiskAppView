package com.example.kinopoiskappview.di

import androidx.lifecycle.ViewModel
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.moviedetail.MovieDetailFragment
import com.example.kinopoiskappview.presentation.moviedetail.MovieDetailViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(
    modules = [MovieDetailModule::class]
)
interface MovieDetailComponent {

    fun inject(fragment: MovieDetailFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance @MovieQualifier movie: Movie,
        ): MovieDetailComponent
    }
}

@Module
interface MovieDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel
}