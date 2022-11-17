package com.rkhvstnv.testecommerce.cart

import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCart

internal interface ProductAdapterCallback {
    fun onAmountChange(productInCart: ProductInCart, newValue: Int)
    fun onDeleteClick(productInCart: ProductInCart)
}