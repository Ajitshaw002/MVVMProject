package com.example.movieproject

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import android.os.Build
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.movieproject.databinding.ActivityDetailBinding
import com.example.movieproject.model.Result
import android.content.Intent




class DetailActivity : AppCompatActivity() {
        lateinit var activityDetailBinding: ActivityDetailBinding
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding =DataBindingUtil.setContentView(this,R.layout.activity_detail)
        //set toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val extras = intent.extras
        val resultsR = extras!!.getSerializable("data") as Result
        activityDetailBinding.result = resultsR

        val imagePath = "https://image.tmdb.org/t/p/w500" +resultsR.backdropPath
        //get the transaction name from previous page
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras.getString("imageview")
            img_poster.transitionName = imageTransitionName
        }
        //load image
        Glide.with(this)
            .load(imagePath)
            .into(img_poster)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home ->{
                onBackPressed()
                return true
            }
                else-> {
                    return super.onOptionsItemSelected(item)
                }
        }
    }
}
