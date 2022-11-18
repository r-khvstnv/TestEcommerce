package com.rkhvstnv.testecommerce.core_data.data.models

import androidx.annotation.DrawableRes

data class CategoryDto(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
)