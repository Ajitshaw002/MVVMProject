package com.example.movieproject.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieproject.adapter.RecyclerMovieAdapter
import com.example.movieproject.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.core.view.ViewCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagedList
import com.example.movieproject.R
import com.example.movieproject.di.DaggerRecyclerMovieComponent
import com.example.movieproject.di.RecyclerMovieComponent
import com.example.movieproject.di.RecyclerMovieModule
import com.example.movieproject.model.Result
import javax.inject.Inject


class MainActivity : AppCompatActivity(), RecyclerMovieAdapter.RecyclerItemClicked {


    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var myMovieList:PagedList<Result>
    lateinit var recyclerMovieComponent: RecyclerMovieComponent

    @Inject
    lateinit var  adapter:RecyclerMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get view model instance
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        //get the object of the Recycler View Adapter using dagger 2
        recyclerMovieComponent = DaggerRecyclerMovieComponent.builder()
            .recyclerMovieModule(RecyclerMovieModule(this,this))
            .build()
        //inject the main activity
        recyclerMovieComponent.inject(this)
        //observe movie data
        observceMovieData()
    }

    private fun observceMovieData() {
//        mainActivityViewModel.getAllMovies(this)?.observe(this, Observer { resultList ->
//            if (resultList != null) {
//                // setRecyclerView
//                setRecyclerView(resultList)
//            }
//        })
        mainActivityViewModel.getMoviePagedList(this).observe(this, Observer { movieList->
            myMovieList =movieList
            setRecyclerView(myMovieList)

        })
    }

    private fun setRecyclerView(resultList: PagedList<Result>?) {
        // Creates a vertical Layout Manager

        adapter.submitList(resultList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerMovie.setLayoutManager(GridLayoutManager(this, 2))
        } else {
            recyclerMovie.setLayoutManager(GridLayoutManager(this, 4))
        }
        recyclerMovie.itemAnimator = DefaultItemAnimator()
        recyclerMovie.adapter = adapter
    }

    override fun onRecyclerItemClicked(position: Int, resultSet: Result?, imgPoster: AppCompatImageView) {
        //start a intent with shared transaction
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data", resultSet)
        intent.putExtra("imageview", ViewCompat.getTransitionName(imgPoster).toString())
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imgPoster,
            ViewCompat.getTransitionName(imgPoster).toString()
        )


        startActivity(intent, options.toBundle())
    }
}
