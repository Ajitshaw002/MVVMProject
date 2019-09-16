package com.example.movieproject.service

import com.example.movieproject.model.MovieListExample
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {

    @GET("popular")
    fun getAllMovieData(@Query("api_key")  apiKeys:String):Call<MovieListExample>

    @GET("upcoming")
    fun getAllMovieDataWithPaging(@Query("api_key")  apiKeys:String,@Query("page")  page:Long):Call<MovieListExample>
}