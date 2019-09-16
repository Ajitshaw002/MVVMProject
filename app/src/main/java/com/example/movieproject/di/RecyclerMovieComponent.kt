package com.example.movieproject.di

import dagger.Component
import javax.inject.Singleton
import com.example.movieproject.MainActivity



@Singleton
@Component(modules = arrayOf(RecyclerMovieModule::class,MovieListServiceModule::class))
interface RecyclerMovieComponent {

    fun inject(activity: MainActivity)
}