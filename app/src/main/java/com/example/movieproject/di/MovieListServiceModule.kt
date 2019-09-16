package com.example.movieproject.di

import android.app.Application
import com.example.movieproject.MyApplication
import com.example.movieproject.RetrofitInstance
import com.example.movieproject.paging.DataSourceFactory
import com.example.movieproject.service.MovieListService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieListServiceModule(var application: Application) {

    lateinit var movieListService: MovieListService

    @Singleton
    @Provides
    fun provideMovieListServiceModule(): MovieListService{
        movieListService = RetrofitInstance.movieListService
        return  movieListService
    }

    @Singleton
    @Provides
    fun provideDataSourceFactory(): DataSourceFactory{
        return DataSourceFactory(movieListService,application)
    }
}