package com.rkhvstnv.testecommerce.core_data.domain.model

data class HotSale(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean,
    val image: String,
    val title: String,
    val subtitle: String,
)