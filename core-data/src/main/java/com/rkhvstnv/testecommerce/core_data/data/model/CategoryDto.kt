package com.rkhvstnv.testecommerce.core_data.data.model

import androidx.annotation.DrawableRes

data class CategoryDto(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
)