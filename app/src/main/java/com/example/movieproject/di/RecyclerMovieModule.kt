package com.example.movieproject.di

import android.content.Context
import com.example.movieproject.adapter.RecyclerMovieAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RecyclerMovieModule(var context: Context,var itemClicked: RecyclerMovieAdapter.RecyclerItemClicked) {

    @Singleton
    @Provides
    fun provideContext():Context{
        return context
    }
    @Singleton
    @Provides
    fun provideRecyclerItemClicked():RecyclerMovieAdapter.RecyclerItemClicked{
        return itemClicked
    }

    @Singleton
    @Provides
    fun provideRecyclerAdapter():RecyclerMovieAdapter{
        return RecyclerMovieAdapter(context,itemClicked)
    }
}