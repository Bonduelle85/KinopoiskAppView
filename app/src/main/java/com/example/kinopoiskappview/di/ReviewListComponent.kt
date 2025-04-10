package com.example.kinopoiskappview.di

import androidx.lifecycle.ViewModel
import com.example.kinopoiskappview.domain.model.Movie
import com.example.kinopoiskappview.presentation.reviewlist.ReviewListFragment
import com.example.kinopoiskappview.presentation.reviewlist.ReviewListViewModel
import com.example.kinopoiskappview.presentation.trailerlist.TrailerListFragment
import com.example.kinopoiskappview.presentation.trailerlist.TrailerListViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(
    modules = [ReviewListModule::class]
)
interface ReviewListComponent {

    fun inject(fragment: ReviewListFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance @MovieQualifier movie: Movie,
        ): ReviewListComponent
    }
}

@Module
interface ReviewListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReviewListViewModel::class)
    fun bindReviewListViewModel(viewModel: ReviewListViewModel): ViewModel
}