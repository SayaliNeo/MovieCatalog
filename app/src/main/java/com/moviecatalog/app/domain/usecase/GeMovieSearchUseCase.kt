package com.moviecatalog.app.domain.usecase

import com.moviecatalog.app.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class GeMovieSearchUseCase
    @Inject
    constructor(
        private val mrepository: MovieRepository
    ): UseCase<GeMovieSearchUseCase.Params, Any>(Dispatchers.IO){

    data class Params(
        val index: Int,
        val input: String,
    )

    override suspend fun execute(params: Params)= mrepository.getFilteredMovieList(params.index, params.input)

}