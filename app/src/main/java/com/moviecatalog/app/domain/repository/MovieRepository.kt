package com.moviecatalog.app.domain.repository

import com.moviecatalog.app.domain.model.Movie
import com.moviecatalog.app.domain.model.Movies


interface MovieRepository {
    suspend fun getData(): Movie


    suspend fun getFilteredMovieList(
        index: Int,
        input: String,
    ): Movie


    suspend fun getStatsCount(list:List<Movies>):List<Map.Entry<Char,Int>>
}
