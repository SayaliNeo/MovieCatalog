package com.moviecatalog.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviecatalog.app.R
import com.moviecatalog.app.databinding.CardItemBinding
import com.moviecatalog.app.domain.model.Movies

class MovieListViewAdapter(private var movieList:List<Movies>):RecyclerView.Adapter<MovieListViewAdapter.ListViewHolder>() {
    
    inner class ListViewHolder(private var mBinding: CardItemBinding):RecyclerView.ViewHolder(mBinding.root) {
        fun setData(movies: Movies) {
            mBinding.movieName.text = movies.title
            mBinding.movieDetail.text = movies.subTitle
            mBinding.movieImage.setBackgroundResource(movies.icon)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val mBinding = DataBindingUtil.inflate<CardItemBinding>(LayoutInflater.from(parent.context),
            R.layout.card_item,parent,false)
        return ListViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.setData(movieList[position])
        
    }

    fun update(movieList: List<Movies>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}