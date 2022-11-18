package com.rkhvstnv.testecommerce.core_data.data.sources

import com.rkhvstnv.testecommerce.core_data.data.models.PhoneDto
import com.rkhvstnv.testecommerce.core_data.data.models.Pojo
import com.rkhvstnv.testecommerce.core_data.data.network.MockyService
import retrofit2.Response
import javax.inject.Inject

internal class RemoteSource @Inject constructor(
    private val service: MockyService
) {
    suspend fun getPojo(): Response<Pojo> = service.getPojo()

    suspend fun getPhone(): Response<PhoneDto> = service.getPhone()
}