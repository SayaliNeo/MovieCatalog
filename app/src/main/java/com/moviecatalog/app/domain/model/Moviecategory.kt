package com.moviecatalog.app.domain.model

import androidx.annotation.DrawableRes

data class Moviecategory (
    val id: MovieYear,
    var movieList: List<Movies>,
    val MovieName: Int,
    @DrawableRes val image: Int

)
