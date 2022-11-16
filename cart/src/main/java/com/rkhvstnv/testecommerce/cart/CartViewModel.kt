package com.rkhvstnv.testecommerce.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCart
import com.rkhvstnv.testecommerce.core_data.domain.usecase.AddToCartUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetAllProductsInCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CartViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val getAllProductsInCartUseCase: GetAllProductsInCartUseCase
): ViewModel() {
    var _allProductsInCartResult: MutableLiveData<MyResult<List<ProductInCart>>> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch(Dispatchers.IO){
            val data = getAllProductsInCartUseCase.invoke()
            _allProductsInCartResult.postValue(data)
        }
    }
}