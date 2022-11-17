package com.rkhvstnv.testecommerce.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCart
import com.rkhvstnv.testecommerce.core_data.domain.usecase.AddToCartUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetAllProductsInCartUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.UpdateCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CartViewModel @Inject constructor(
    private val updateCartUseCase: UpdateCartUseCase,
    private val getAllProductsInCartUseCase: GetAllProductsInCartUseCase
): ViewModel() {
    private var _inCartResult: MutableLiveData<MyResult<List<ProductInCart>>> = MutableLiveData()
    val inCartResult: LiveData<MyResult<List<ProductInCart>>> get() = _inCartResult
    private var _allProductsInCart: MutableLiveData<List<ProductInCart>> = MutableLiveData()
    val allProductsInCart: LiveData<List<ProductInCart>> get() = _allProductsInCart
    private var _totalCost: MutableLiveData<Int> = MutableLiveData()
    val totalCost: LiveData<Int> get() = _totalCost

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch(Dispatchers.IO){
            val data = getAllProductsInCartUseCase.invoke()
            _inCartResult.postValue(data)
            if (data is MyResult.Success){
                _allProductsInCart.postValue(data.data)
            }
        }
    }

   fun changeProductInCartAmount(productInCart: ProductInCart, amount: Int){
       if (_allProductsInCart.value != null){
           val newList = _allProductsInCart.value!!.toMutableList()


           val position = newList.indexOf(productInCart)
           if (position >= 0 && amount > 0){
               newList[position] = ProductInCart(
                   id = productInCart.id,
                   amount = amount,
                   name = productInCart.name,
                   price = productInCart.price,
                   image = productInCart.image,
               )
           }


           _allProductsInCart.postValue(newList.toList())
       }
   }

    fun removeProductFromCart(productInCart: ProductInCart){
        if (_allProductsInCart.value != null){
            val newList = _allProductsInCart.value!!.toMutableList()

            newList.remove(productInCart)

            _allProductsInCart.postValue(newList.toList())
        }
    }

    fun estimateTotalCost(){
        _allProductsInCart.value?.let {
            list ->
            var total: Int = 0
            for (item in list){
                total += item.totalCost
            }
            _totalCost.postValue(total)
        }
    }

    fun updateProductsInCart(){
        _allProductsInCart.value?.let {
            updateCartUseCase.invoke(list = it)
        }
    }
}