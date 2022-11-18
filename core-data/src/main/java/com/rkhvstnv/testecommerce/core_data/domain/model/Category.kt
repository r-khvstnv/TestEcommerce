package com.rkhvstnv.testecommerce.core_data.domain.model

import androidx.annotation.DrawableRes
import com.rkhvstnv.testecommerce.core_data.DisplayableItem

data class Category(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    var isSelected: Boolean = false
): DisplayableItem