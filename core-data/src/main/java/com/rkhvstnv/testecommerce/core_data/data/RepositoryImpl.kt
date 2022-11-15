package com.rkhvstnv.testecommerce.core_data.data

import com.rkhvstnv.testecommerce.core_data.data.source.LocalSource
import com.rkhvstnv.testecommerce.core_data.data.source.RemoteSource
import com.rkhvstnv.testecommerce.core_data.data.utils.PojoNetworkResultToBestSellerProductListResultMapper
import com.rkhvstnv.testecommerce.core_data.data.utils.PojoNetworkResultToHotSaleListResultMapper
import com.rkhvstnv.testecommerce.core_data.data.utils.handleApi
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.BestSellerProduct
import com.rkhvstnv.testecommerce.core_data.domain.model.HotSale
import com.rkhvstnv.testecommerce.core_data.domain.model.Product
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
): Repository {
    private val pojoNetworkResultToHotSaleListResultMapper = PojoNetworkResultToHotSaleListResultMapper()
    private val pojoNetworkResultToBestSellerProductListResultMapper = PojoNetworkResultToBestSellerProductListResultMapper()

    override suspend fun getHotSales(): MyResult<List<HotSale>> = handleApi {
        remoteSource.getPojo() }.let(pojoNetworkResultToHotSaleListResultMapper::map)

    override suspend fun getBestSellers(): MyResult<List<BestSellerProduct>> =  handleApi {
        remoteSource.getPojo() }.let(pojoNetworkResultToBestSellerProductListResultMapper::map)

    override suspend fun getProductsInCart(): List<Product> = listOf(Product(1,1))
}