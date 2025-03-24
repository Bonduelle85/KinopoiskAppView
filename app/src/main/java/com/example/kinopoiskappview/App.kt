package com.example.kinopoiskappview

import android.app.Application
import com.example.kinopoiskappview.di.ApplicationComponent
import com.example.kinopoiskappview.di.DaggerApplicationComponent

class App : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}