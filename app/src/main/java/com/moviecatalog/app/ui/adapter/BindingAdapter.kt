package com.moviecatalog.app.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.moviecatalog.app.domain.model.Movie
import com.moviecatalog.app.ui.adapter.BindingAdapter.displayViewList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setHorizontalPager")
    fun ViewPager2.setHorizontalPager(movieList: StateFlow<Movie>?) {
        val lifecycleOwner = context as? LifecycleOwner
        lifecycleOwner?.lifecycleScope?.launch {
            movieList?.collect { list ->
                if (list.movieList.isNotEmpty()) {
                    val adapter = this@setHorizontalPager.adapter as? MovieSliderAdapter
                    if (adapter == null) {
                        this@setHorizontalPager.adapter = MovieSliderAdapter(list.movieList.map { movies -> movies.image })
                    } else {
                        adapter.updateData(list.movieList.map { movieList -> movieList.image })
                    }
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("index", "displayViewList")
    fun RecyclerView.displayViewList(index: Int, movieList: StateFlow<Movie>) {
        val lifecycleOwner = context as LifecycleOwner
        lifecycleOwner.lifecycleScope.launch {
            movieList.collect { list ->
                if (list.movieList.isNotEmpty()) {
                    val adapter = this@displayViewList.adapter as? MovieListViewAdapter
                    if (adapter == null) {
                        this@displayViewList.adapter =
                            MovieListViewAdapter(list.movieList[index].movieList)
                    } else {
                        adapter.update(list.movieList[index].movieList)
                    }
                }
            }


        }

    }

}
