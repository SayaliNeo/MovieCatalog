package com.moviecatalog.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.app.domain.model.Movie
import com.moviecatalog.app.domain.model.Moviecategory
import com.moviecatalog.app.domain.model.Movies
import com.moviecatalog.app.domain.usecase.GeMovieSearchUseCase
import com.moviecatalog.app.domain.usecase.GetMovieInfoUseCase
import com.moviecatalog.app.domain.usecase.GetMovieStatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val getMovieInfoUseCase: GetMovieInfoUseCase,
    private val getMovieSearchUseCase: GeMovieSearchUseCase,
    val getMovieStatsUseCase: GetMovieStatsUseCase

    ): ViewModel(){

    private val movieDataHolder = MutableStateFlow(Movie(emptyList()))
    val movieData: StateFlow<Movie> = movieDataHolder.asStateFlow()
    private val charCount = MutableSharedFlow<List<Map.Entry<Char, Int>>>()
    val topCharCount: SharedFlow<List<Map.Entry<Char, Int>>> = charCount.asSharedFlow()

    fun getMovieList(movie:String=""){
        viewModelScope.launch { movieDataHolder.emit(getMovieInfoUseCase.execute(GetMovieInfoUseCase.Params(movie))) }
    }
    fun getFilteredData(
        index: Int,
        input: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDataHolder.emit(getMovieSearchUseCase.execute(GeMovieSearchUseCase.Params(index, input)))
        }
    }

    fun getMovieStatsCount(list:List<Movies>){
        viewModelScope.launch(Dispatchers.IO) {
            charCount.emit(getMovieStatsUseCase.execute(GetMovieStatsUseCase.Params(list)))
        }
    }
}
   //9604665531
