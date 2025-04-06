package com.example.kinopoiskappview.di

import androidx.lifecycle.ViewModel
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.trailerlist.TrailerListFragment
import com.example.kinopoiskappview.presentation.trailerlist.TrailerListViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(
    modules = [TrailerListModule::class]
)
interface TrailerListComponent {

    fun inject(fragment: TrailerListFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance @MovieQualifier movie: Movie,
        ): TrailerListComponent
    }
}

@Module
interface TrailerListModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrailerListViewModel::class)
    fun bindTrailerListViewModel(viewModel: TrailerListViewModel): ViewModel
}