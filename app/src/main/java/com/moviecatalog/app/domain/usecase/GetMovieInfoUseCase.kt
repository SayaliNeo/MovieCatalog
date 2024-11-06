package com.moviecatalog.app.domain.usecase

import com.moviecatalog.app.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieInfoUseCase
    @Inject
    constructor(
        private val mrepository: MovieRepository): UseCase<GetMovieInfoUseCase.Params, Any>(Dispatchers.IO){

        override suspend fun execute(params: Params)= mrepository.getData()


        data class Params(val name:String)

}


