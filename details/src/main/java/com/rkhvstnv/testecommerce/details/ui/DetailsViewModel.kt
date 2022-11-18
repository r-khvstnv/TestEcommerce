package com.rkhvstnv.testecommerce.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.models.Phone
import com.rkhvstnv.testecommerce.core_data.domain.models.ProductInCartDto
import com.rkhvstnv.testecommerce.core_data.domain.usecases.AddToCartUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecases.GetPhoneByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailsViewModel @Inject constructor(
    private val getPhoneByIdUseCase: GetPhoneByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase
): ViewModel() {
    private var _phoneResult: MutableLiveData<MyResult<Phone>> = MutableLiveData()
    val phoneResult: LiveData<MyResult<Phone>> get() = _phoneResult


    /**
     * Method requests [Phone] from sources using [id].
     * Received complex data will be assign to [_phoneResult] as [MyResult].
     * */
    fun requestPhoneDataById(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            val data = getPhoneByIdUseCase.invoke(id = id)
            _phoneResult.postValue(data)
        }
    }

    /**
     * Method adds [Phone] from [_phoneResult] to source.
     * */
    fun addPhoneToCart(){
        _phoneResult.value?.let {
            if (it is MyResult.Success){
                val productInCartDto = ProductInCartDto(
                    id = it.data.id.toInt(),
                    amount = 1
                )
                addToCartUseCase.invoke(productInCartDto = productInCartDto)
            }
        }
    }
}