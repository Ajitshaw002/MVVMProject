package com.example.movieproject

import android.util.Log
import com.example.movieproject.service.MovieListService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val movieListService: MovieListService by lazy {
        Log.d("WebAccess", "Creating retrofit client")
        val retrofit = Retrofit.Builder()

            .baseUrl("https://api.themoviedb.org/3/movie/")
            // Gson maps JSON to classes
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        // Create Retrofit client
        return@lazy retrofit.create(MovieListService::class.java)
    }
}