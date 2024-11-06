package com.moviecatalog.app.domain.usecase

import com.moviecatalog.app.domain.model.Movies
import com.moviecatalog.app.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieStatsUseCase @Inject constructor(private val mRepository:MovieRepository):
UseCase<GetMovieStatsUseCase.Params,Any>(Dispatchers.IO){
    class Params (val movie:List<Movies>)

    override suspend fun execute(params: Params) = mRepository.getStatsCount(params.movie)

}