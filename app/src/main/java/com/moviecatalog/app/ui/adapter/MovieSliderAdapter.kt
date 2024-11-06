package com.moviecatalog.app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviecatalog.app.R
import com.moviecatalog.app.databinding.SliderItemBinding

class MovieSliderAdapter(private var movieDetails:List<Int>):RecyclerView.Adapter<MovieSliderAdapter.MoviePagerViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(images: List<Int>) {
        movieDetails = images
        notifyDataSetChanged()
    }

    inner class MoviePagerViewHolder(private val mBinding:SliderItemBinding):RecyclerView.ViewHolder(mBinding.root) {
        fun setData(sliderImage: Int) {
            mBinding.imageView.setBackgroundResource(sliderImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePagerViewHolder {
        val mBinding = DataBindingUtil.inflate<SliderItemBinding>(LayoutInflater.from(parent.context),
            R.layout.slider_item,parent,false)
        return  MoviePagerViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return movieDetails.size
    }

    override fun onBindViewHolder(holder: MoviePagerViewHolder, position: Int) {
        holder.setData(movieDetails[position])
        
    }

}