package com.example.movieproject.viewmodel

import android.app.AppComponentFactory
import android.app.Application
import android.app.IntentService
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movieproject.repositatory.MainActivityRepo
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieproject.RetrofitInstance
import com.example.movieproject.di.DaggerViewModelComponent
import com.example.movieproject.di.MovieListServiceModule
import com.example.movieproject.di.ViewModelComponent
import com.example.movieproject.model.Result
import com.example.movieproject.paging.DataSourceFactory
import com.example.movieproject.paging.MovieDataSource
import com.example.movieproject.service.MovieListService
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    // var mainActivityRepo: MainActivityRepo
    @Inject
    lateinit var movieListService: MovieListService

    @Inject
    lateinit var dataSourceFactory: DataSourceFactory

     var viewModelComponent: ViewModelComponent

    var executer: Executor
    var pageListMovie: LiveData<PagedList<Result>>//create a Live data of Paged list

    init {

        viewModelComponent = DaggerViewModelComponent.builder()
            .movieListServiceModule(MovieListServiceModule(application))
            .build()


        viewModelComponent.inject(this)

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)//Defines how many items to load when first load occurs.
            .setPageSize(20)//Defines the number of items loaded at once from the DataSource.
            .setPrefetchDistance(4)//Defines how far from the edge of loaded content an access must be to trigger further loading.
            .build()

        executer = Executors.newFixedThreadPool(5)
        pageListMovie = LivePagedListBuilder<Long, Result>(dataSourceFactory, config)//create a live data of LIVE-PAGED-LIST-BUILDER
            .setFetchExecutor(executer)
            .build()

    }

//    fun getAllMovies(context: Context): LiveData<List<Result>>? {
//        if (isNetworkAvailable(context)) {
//            return mainActivityRepo.getMutableLiveData()
//        }else{
//            Toast.makeText(context,"Please check internet connection", Toast.LENGTH_LONG).show()
//            return null
//        }
//    }


    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    fun getMoviePagedList(context: Context): LiveData<PagedList<Result>> {
        if (isNetworkAvailable(context)) {
            return pageListMovie
        } else {
            Toast.makeText(context, "Please check internet connection", Toast.LENGTH_LONG).show()
            return pageListMovie
        }

    }
}