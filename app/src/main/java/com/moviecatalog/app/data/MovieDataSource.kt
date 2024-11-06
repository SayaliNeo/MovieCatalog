package com.moviecatalog.app.data

import com.moviecatalog.app.domain.model.Movie
import com.moviecatalog.app.domain.model.MovieYear
import com.moviecatalog.app.domain.model.Movies

interface MovieDataSource {
    suspend fun getData(): Movie

    suspend fun getMovieListToView(
        movieYear: MovieYear,
        icon: Int,
    ): List<Movies>

    suspend fun filterList(
        index: Int,
        input: String,
    ): Movie

    suspend fun getStatisticCount(list:List<Movies>):List<Map.Entry<Char,Int>>

}