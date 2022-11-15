package com.rkhvstnv.testecommerce.core_data.domain.model

import androidx.annotation.DrawableRes

data class Category(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
)