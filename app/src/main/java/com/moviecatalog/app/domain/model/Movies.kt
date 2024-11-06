package com.moviecatalog.app.domain.model

import androidx.annotation.DrawableRes

data class Movies(
    val subTitle: String,
    val title: String,
    @DrawableRes val icon: Int,
)
