package com.example.movieproject.paging

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movieproject.model.Result
import com.example.movieproject.service.MovieListService


class DataSourceFactory(var movieListService: MovieListService, var application: Application)
    : DataSource.Factory<Long, Result>(){

        private lateinit var movieDataSource: MovieDataSource
    private  var mutableLiveData: MutableLiveData<MovieDataSource>
    init {
        mutableLiveData = MutableLiveData()
    }
    override fun create(): DataSource<Long, Result> {
        movieDataSource = MovieDataSource(movieListService,application)
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }

    fun getMutableLiveData():MutableLiveData<MovieDataSource>{
        return   mutableLiveData
    }

}