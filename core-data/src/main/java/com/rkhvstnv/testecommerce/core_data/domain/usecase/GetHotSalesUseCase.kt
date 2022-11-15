package com.rkhvstnv.testecommerce.core_data.domain.usecase

import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.HotSale
import javax.inject.Inject

class GetHotSalesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): MyResult<List<HotSale>> = repository.getHotSales()
}