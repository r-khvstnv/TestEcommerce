package com.rkhvstnv.testecommerce.core_data.data.source

import com.rkhvstnv.testecommerce.core_data.data.model.PhoneDto
import com.rkhvstnv.testecommerce.core_data.data.model.Pojo
import com.rkhvstnv.testecommerce.core_data.data.network.MockyService
import retrofit2.Response
import javax.inject.Inject

internal class RemoteSource @Inject constructor(
    private val service: MockyService
) {
    suspend fun getPojo(): Response<Pojo> = service.getPojo()

    suspend fun getPhone(): Response<PhoneDto> = service.getPhone()
}