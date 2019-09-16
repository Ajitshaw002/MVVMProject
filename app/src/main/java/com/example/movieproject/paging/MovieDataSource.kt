package com.example.movieproject.paging

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.paging.PageKeyedDataSource
import com.example.movieproject.R
import com.example.movieproject.RetrofitInstance
import com.example.movieproject.model.MovieListExample
import com.example.movieproject.model.Result
import com.example.movieproject.service.MovieListService
import retrofit2.Response


class MovieDataSource(var movieListService:MovieListService,var application: Application) : PageKeyedDataSource<Long, Result>() {

    var arrayList:ArrayList<Result> = ArrayList()

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Result>) {
        movieListService = RetrofitInstance.movieListService
        val call: retrofit2.Call<MovieListExample> = movieListService.getAllMovieDataWithPaging(application.getString(
            R.string.api_key
        ),1)
        call.enqueue(object : retrofit2.Callback<MovieListExample> {
            override fun onFailure(call: retrofit2.Call<MovieListExample>, t: Throwable) {
                Log.d("WebAccess", "fail")
                Toast.makeText(application,"Some problem occur please try after somtime", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: retrofit2.Call<MovieListExample>, response: Response<MovieListExample>) {
                if (response.body()!= null) {
                    arrayList = (response.body()!!.results as? ArrayList<Result>)!!
                    callback.onResult(arrayList,null,2 )
                }
            }

        })

    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Result>) {
        movieListService = RetrofitInstance.movieListService
        val call: retrofit2.Call<MovieListExample> = movieListService.getAllMovieDataWithPaging(application.getString(
            R.string.api_key
        ),params.key)
        call.enqueue(object : retrofit2.Callback<MovieListExample> {
            override fun onFailure(call: retrofit2.Call<MovieListExample>, t: Throwable) {
                Log.d("WebAccess", "fail")
                Toast.makeText(application,"Some problem occur please try after somtime", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: retrofit2.Call<MovieListExample>, response: Response<MovieListExample>) {
                if (response.body()!= null) {
                    arrayList = (response.body()!!.results as? ArrayList<Result>)!!
                    callback.onResult(arrayList,params.key+1)
                }
            }

        })

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Result>) {
    }


}