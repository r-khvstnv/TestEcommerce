package com.rkhvstnv.testecommerce.core_data.domain.usecases

import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.models.ProductInCart
import com.rkhvstnv.testecommerce.core_data.domain.models.ProductInCartDto
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(list: List<ProductInCart>){
        val dtoList = mutableListOf<ProductInCartDto>()
        for (item in list){
            dtoList.add(
                ProductInCartDto(
                    id = item.id,
                    amount = item.amount
                )
            )
        }
        repository.insertAllProductsInCart(list = dtoList)
    }
}