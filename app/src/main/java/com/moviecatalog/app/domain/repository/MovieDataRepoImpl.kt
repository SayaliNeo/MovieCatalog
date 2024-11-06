package com.moviecatalog.app.domain.repository

import com.moviecatalog.app.data.MovieDataSource
import com.moviecatalog.app.domain.model.Movie
import com.moviecatalog.app.domain.model.Movies
import javax.inject.Inject


class MovieDataRepoImpl
    @Inject
constructor(private val movieDataSource: MovieDataSource):MovieRepository{

    override suspend fun getData(): Movie {
        return movieDataSource.getData()
    }

    override suspend fun getFilteredMovieList(index: Int, input: String): Movie {
        return  movieDataSource.filterList(index, input)
    }

    override suspend fun getStatsCount(list: List<Movies>): List<Map.Entry<Char, Int>> {
        return movieDataSource.getStatisticCount(list)
    }

    suspend fun getStatisticCount(movie:List<Movies>):List<Map.Entry<Char,Int>> = movieDataSource.getStatisticCount(movie)


}