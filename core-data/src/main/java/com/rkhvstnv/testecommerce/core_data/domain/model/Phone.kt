package com.rkhvstnv.testecommerce.core_data.domain.model

data class Phone(
    val id: String,
    val isFavorites: Boolean,
    val title: String,
    val images: List<String>,
    val cpu: String,
    val camera: String,
    val ram: String,
    val sd: String,
    val capacity: List<String>,
    val color: List<String>,
    val price: Int,
    val rating: Double,
)