package com.rkhvstnv.testecommerce.core_data.domain.usecases

import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.models.Category
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