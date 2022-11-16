package com.rkhvstnv.testecommerce.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.model.Phone
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetPhoneByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailsViewModel @Inject constructor(
    private val getPhoneByIdUseCase: GetPhoneByIdUseCase
): ViewModel() {
    private var _phone: MutableLiveData<MyResult<Phone>> = MutableLiveData()
    val phone: LiveData<MyResult<Phone>> get() = _phone


    fun requestPhoneDataById(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            val data = getPhoneByIdUseCase.invoke(id = id)
            _phone.postValue(data)
        }
    }
}