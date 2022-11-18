package com.rkhvstnv.testecommerce.core_data.domain

import com.rkhvstnv.testecommerce.core_data.data.model.CategoryDto
import com.rkhvstnv.testecommerce.core_data.domain.model.*

interface Repository {
    suspend fun getHotSales(): MyResult<List<HotSale>>
    suspend fun getBestSellers(): MyResult<List<BestSellerProduct>>
    suspend fun getPhoneById(id: Int): MyResult<Phone>

    fun getProductsInCart(): MyResult<List<ProductInCartDto>>
    fun insertAllProductsInCart(list: List<ProductInCartDto>)
    fun getAllCategories(): List<Category>
}