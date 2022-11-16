package com.rkhvstnv.testecommerce.core_data.domain.usecase

import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.Phone
import javax.inject.Inject

class GetPhoneByIdUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(id: Int): MyResult<Phone> = repository.getPhoneById(id = id)
}