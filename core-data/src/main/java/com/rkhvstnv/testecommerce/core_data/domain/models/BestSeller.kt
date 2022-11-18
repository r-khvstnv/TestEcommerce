package com.rkhvstnv.testecommerce.core_data.domain.models

data class BestSeller(
    val id: Int,
    val is_favorites: Boolean,
    val image: String,
    val title: String,
    val price_without_discount: Int,
    val discount_price: Int,
): DisplayableItem