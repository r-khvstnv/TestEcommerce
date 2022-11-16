package com.rkhvstnv.testecommerce.core_data.domain

import com.rkhvstnv.testecommerce.core_data.domain.model.BestSellerProduct
import com.rkhvstnv.testecommerce.core_data.domain.model.HotSale
import com.rkhvstnv.testecommerce.core_data.domain.model.Phone
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCartDto

interface Repository {
    suspend fun getHotSales(): MyResult<List<HotSale>>
    suspend fun getBestSellers(): MyResult<List<BestSellerProduct>>
    suspend fun getPhoneById(id: Int): MyResult<Phone>

    fun getProductsInCart(): MyResult<List<ProductInCartDto>>
    fun insertAllProductsInCart(list: List<ProductInCartDto>)
}