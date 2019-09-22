package com.example.movieproject.repositatory

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.movieproject.R
import com.example.movieproject.RetrofitInstance
import com.example.movieproject.model.MovieListExample
import com.example.movieproject.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class MainActivityRepo(var application: Application){
    var arrayList:ArrayList<Result> = ArrayList()
    var mutablLiveData:MutableLiveData<List<Result>> = MutableLiveData()


    fun getMutableLiveData():MutableLiveData<List<Result>>{
        val callService = RetrofitInstance.movieListService
        val call: Call<MovieListExample> = callService.getAllMovieData(application.getString(R.string.api_key))
        call.enqueue(object : Callback<MovieListExample> {
            override fun onFailure(call: Call<MovieListExample>, t: Throwable) {
                Log.d("WebAccess", "fail")
                Toast.makeText(application,"Some problem occur please try after someetime", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<MovieListExample>, response: Response<MovieListExample>) {
                if (response.body()!= null) {
                    arrayList = (response.body()!!.results as? ArrayList<Result>)!!
                    mutablLiveData.value = arrayList
                }
            }

        })
        return  mutablLiveData
    }


}