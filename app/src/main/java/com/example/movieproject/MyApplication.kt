package com.example.movieproject

import android.app.Application
import com.example.movieproject.di.DaggerRecyclerMovieComponent
import com.example.movieproject.di.RecyclerMovieComponent

class MyApplication : Application() {
    lateinit var recyclerMovieComponent: RecyclerMovieComponent

    companion object {
        lateinit var myAplication: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        myAplication = this

    }
}