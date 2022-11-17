package com.rkhvstnv.testecommerce.core_data.domain.usecase

import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCartDto
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(productInCartDto: ProductInCartDto){
        val productInCartDtoList = arrayListOf<ProductInCartDto>()
        val result = repository.getProductsInCart()
        if (result is MyResult.Success){
            productInCartDtoList.addAll(result.data)
        }

        productInCartDtoList.add(productInCartDto)
        repository.insertAllProductsInCart(productInCartDtoList)
    }
}