package com.rkhvstnv.testecommerce.core_data.domain

import com.rkhvstnv.testecommerce.core_data.domain.model.BestSellerProduct
import com.rkhvstnv.testecommerce.core_data.domain.model.HotSale
import com.rkhvstnv.testecommerce.core_data.domain.model.Phone
import com.rkhvstnv.testecommerce.core_data.domain.model.Product

interface Repository {
    suspend fun getHotSales(): MyResult<List<HotSale>>
    suspend fun getBestSellers(): MyResult<List<BestSellerProduct>>
    suspend fun getProductsInCart(): List<Product>
    suspend fun getPhoneById(id: Int): MyResult<Phone>
}