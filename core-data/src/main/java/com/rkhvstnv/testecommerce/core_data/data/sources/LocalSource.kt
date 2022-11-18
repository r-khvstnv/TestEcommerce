package com.rkhvstnv.testecommerce.core_data.data.sources

import com.rkhvstnv.testecommerce.core_data.data.database.MockDatabase
import com.rkhvstnv.testecommerce.core_data.data.models.CategoryDto
import javax.inject.Inject

internal class LocalSource @Inject constructor(private val mockDatabase: MockDatabase){
    fun insertAllProductInCart(json: String) = mockDatabase.insertAllProductInCart(json = json)
    fun getAllProductsInCart(): String = mockDatabase.getAllProductsInCart()
    fun getAllCategories(): List<CategoryDto> = mockDatabase.getAllCategories()
}