package com.example.kinopoiskappview.di

import android.app.Application
import com.example.kinopoiskappview.data.database.AppDatabase
import com.example.kinopoiskappview.data.database.MoviesDao
import com.example.kinopoiskappview.data.network.ApiFactory
import com.example.kinopoiskappview.data.network.ApiService
import com.example.kinopoiskappview.data.repository.RepositoryImpl
import com.example.kinopoiskappview.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface  DataModule {

    @Binds
    abstract fun bindRepository(impl: RepositoryImpl): Repository

    companion object {

        @Provides
        fun provideMoviesDao(
            application: Application
        ): MoviesDao {
            return AppDatabase.getInstance(application).moviesDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
