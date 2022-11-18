package com.rkhvstnv.testecommerce.core_data.data.source

import com.rkhvstnv.testecommerce.core_data.data.database.MockDatabase
import com.rkhvstnv.testecommerce.core_data.data.model.CategoryDto
import javax.inject.Inject

internal class LocalSource @Inject constructor(private val mockDatabase: MockDatabase){
    fun insertAllProductInCart(json: String) = mockDatabase.insertAllProductInCart(json = json)
    fun getAllProductsInCart(): String = mockDatabase.getAllProductsInCart()
    fun getAllCategories(): List<CategoryDto> = mockDatabase.getAllCategories()
}