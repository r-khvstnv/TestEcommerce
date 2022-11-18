package com.rkhvstnv.testecommerce.core_data.domain.usecase

import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.BestSeller
import javax.inject.Inject

class GetBestSellersUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): MyResult<List<BestSeller>> = repository.getBestSellers()
}