package com.rkhvstnv.testecommerce.cart

import com.rkhvstnv.testecommerce.core.ListAdapterCallBack
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCart

internal interface ProductAdapterCallback: ListAdapterCallBack {
    fun onAmountChange(productInCart: ProductInCart, newValue: Int)
    fun onDeleteClick(productInCart: ProductInCart)
    override fun onClick(id: Int) = Unit
}