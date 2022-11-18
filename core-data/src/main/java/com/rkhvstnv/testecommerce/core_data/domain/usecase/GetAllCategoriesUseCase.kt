package com.rkhvstnv.testecommerce.core_data.domain.usecase

import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.Category
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): List<Category>{
        val list = repository.getAllCategories().toMutableList()
        list[0].isSelected = true
        return list
    }
}