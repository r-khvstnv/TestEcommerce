package com.rkhvstnv.testecommerce.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

internal class DetailsViewModel @Inject constructor(): ViewModel() {
    val testValue = MutableLiveData<String>()
}