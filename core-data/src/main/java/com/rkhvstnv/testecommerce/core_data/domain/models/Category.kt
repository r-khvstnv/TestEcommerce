package com.rkhvstnv.testecommerce.core_data.domain.models

import androidx.annotation.DrawableRes

data class Category(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    var isSelected: Boolean = false
): DisplayableItem