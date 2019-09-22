package com.example.movieproject.di

import com.example.movieproject.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(MovieListServiceModule::class))
interface ViewModelComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}