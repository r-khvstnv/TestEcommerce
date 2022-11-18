package com.rkhvstnv.testecommerce.core_data.data.models

import androidx.annotation.DrawableRes

/**
 * Mock Data class
 * */
internal data class CategoryDto(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
)