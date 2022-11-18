package com.rkhvstnv.testecommerce.core_data.domain

import com.rkhvstnv.testecommerce.core_data.domain.models.*

interface Repository {
    /**
     * Method returns [List] of [HotSale] as [MyResult].
     * */
    suspend fun getHotSales(): MyResult<List<HotSale>>
    /**
     * Method returns [List] of [BestSeller] as [MyResult].
     * */
    suspend fun getBestSellers(): MyResult<List<BestSeller>>
    /**
     * Method request [Phone] by [id] and returns it as [MyResult].
     * */
    suspend fun getPhoneById(id: Int): MyResult<Phone>

    /**
     * Method returns [List] of [ProductInCartDto] as [MyResult].
     * */
    fun getProductsInCart(): MyResult<List<ProductInCartDto>>
    /**
     * Method add to source set [list].
     * */
    fun insertAllProductsInCart(list: List<ProductInCartDto>)
    /**
     * Method returns [List] of [Category].
     * */
    fun getAllCategories(): List<Category>
}
