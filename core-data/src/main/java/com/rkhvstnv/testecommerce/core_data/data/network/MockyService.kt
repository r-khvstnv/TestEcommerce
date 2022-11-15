package com.rkhvstnv.testecommerce.core_data.data.network

import com.rkhvstnv.testecommerce.core_data.data.utils.NetworkConst
import com.rkhvstnv.testecommerce.core_data.data.model.PhoneDto
import com.rkhvstnv.testecommerce.core_data.data.model.Pojo
import retrofit2.Response
import retrofit2.http.GET

internal interface MockyService {
    @GET(NetworkConst.POJO_ENDPOINT)
    suspend fun getPojo(): Response<Pojo>
    @GET(NetworkConst.MOCK_PRODUCT_ENDPOINT)
    suspend fun getPhone(): Response<PhoneDto>

}