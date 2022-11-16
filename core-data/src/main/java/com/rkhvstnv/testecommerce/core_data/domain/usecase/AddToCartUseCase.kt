package com.rkhvstnv.testecommerce.core_data.domain.usecase

import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCartDto
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(productInCartDtoList: List<ProductInCartDto>){
        repository.insertAllProductsInCart(productInCartDtoList)
    }
}