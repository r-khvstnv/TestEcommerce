package com.rkhvstnv.testecommerce.core_data.data.network

import com.rkhvstnv.testecommerce.core_data.data.utils.NetworkConst
import com.rkhvstnv.testecommerce.core_data.data.models.PhoneDto
import com.rkhvstnv.testecommerce.core_data.data.models.Pojo
import retrofit2.Response
import retrofit2.http.GET

/**
 * Mock implementation of Api calls.
 * */
internal interface MockyService {
    @GET(NetworkConst.POJO_ENDPOINT)
    suspend fun getPojo(): Response<Pojo>
    @GET(NetworkConst.MOCK_PRODUCT_ENDPOINT)
    suspend fun getPhone(): Response<PhoneDto>

}