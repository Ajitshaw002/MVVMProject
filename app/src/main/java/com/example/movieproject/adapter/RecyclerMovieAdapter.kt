package com.example.movieproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieproject.R
import com.example.movieproject.model.Result
import kotlinx.android.synthetic.main.recycler_item_movie_list.view.*




class RecyclerMovieAdapter(var ctx:Context,var itemClicked:RecyclerItemClicked) :
    PagedListAdapter<Result,RecyclerMovieAdapter.HolderItems>((Result.CALLBACK)) {



    override fun onBindViewHolder(holder: HolderItems, position: Int) {
        val imagePath = "https://image.tmdb.org/t/p/w500" +getItem(position)!!.posterPath
        Glide.with(ctx)
            .load(imagePath)
            //.placeholder(R.drawable.piwo_48)
            .placeholder(R.drawable.loading)
            .into(holder.img_poster)

        holder.img_poster.setOnClickListener {
            itemClicked.onRecyclerItemClicked(holder.adapterPosition,getItem(position),holder.img_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderItems {
        return HolderItems(LayoutInflater.from(ctx).inflate(R.layout.recycler_item_movie_list, parent, false))
    }


    class HolderItems(view: View):RecyclerView.ViewHolder(view)
    {
        val img_poster = view.img_poster
    }

    interface RecyclerItemClicked{
        fun onRecyclerItemClicked(
            position: Int,
            resultSet: Result?,
            imgPoster: AppCompatImageView
        )
    }
}