package com.rkhvstnv.testecommerce.core_data.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rkhvstnv.testecommerce.core_data.data.source.LocalSource
import com.rkhvstnv.testecommerce.core_data.data.source.RemoteSource
import com.rkhvstnv.testecommerce.core_data.data.utils.*
import com.rkhvstnv.testecommerce.core_data.data.utils.PhoneDtoResultToPhoneResultMapper
import com.rkhvstnv.testecommerce.core_data.data.utils.PojoNetworkResultToBestSellerProductListResultMapper
import com.rkhvstnv.testecommerce.core_data.data.utils.PojoNetworkResultToHotSaleListResultMapper
import com.rkhvstnv.testecommerce.core_data.data.utils.handleApi
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.*
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
): Repository {
    private val pojoNetworkResultToHotSaleListResultMapper = PojoNetworkResultToHotSaleListResultMapper()
    private val pojoNetworkResultToBestSellerProductListResultMapper = PojoNetworkResultToBestSellerProductListResultMapper()
    private val phoneDtoResultToPhoneResultMapper = PhoneDtoResultToPhoneResultMapper()
    private val categoryDtoToCategoryMapper = CategoryDtoToCategoryMapper()

    override suspend fun getHotSales(): MyResult<List<HotSale>> = handleApi {
        remoteSource.getPojo() }.let(pojoNetworkResultToHotSaleListResultMapper::map)

    override suspend fun getBestSellers(): MyResult<List<BestSeller>> =  handleApi {
        remoteSource.getPojo() }.let(pojoNetworkResultToBestSellerProductListResultMapper::map)

    /*For test purpose, every time, will be returned the same mock data.*/
    override suspend fun getPhoneById(id: Int): MyResult<Phone> = handleApi {
        remoteSource.getPhone() }.let(phoneDtoResultToPhoneResultMapper::map)

    override fun getProductsInCart(): MyResult<List<ProductInCartDto>> {
        val json = localSource.getAllProductsInCart()
        return if (json.isEmpty()){
            MyResult.Error(0, "No data")
        } else{
            val itemType = object : TypeToken<List<ProductInCartDto>>(){}.type
            MyResult.Success(data = Gson().fromJson(json, itemType))
        }
    }

    override fun insertAllProductsInCart(list: List<ProductInCartDto>) {
        localSource.insertAllProductInCart(json = Gson().toJson(list))
    }

    override fun getAllCategories(): List<Category> = localSource.getAllCategories().map {
        categoryDtoToCategoryMapper.map(input = it)
    }
}