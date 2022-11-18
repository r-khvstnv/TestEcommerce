package com.rkhvstnv.testecommerce.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.models.BestSeller
import com.rkhvstnv.testecommerce.core_data.domain.models.Category
import com.rkhvstnv.testecommerce.core_data.domain.models.HotSale
import com.rkhvstnv.testecommerce.core_data.domain.usecases.GetAllCategoriesUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecases.GetBestSellersUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecases.GetHotSalesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getHotSalesUseCase: GetHotSalesUseCase,
    private val getBestSellersUseCase: GetBestSellersUseCase
): ViewModel() {
    private var _allCategories: MutableLiveData<List<Category>> = MutableLiveData()
    val allCategories: LiveData<List<Category>> get() = _allCategories
    private var _hotSalesResult: MutableLiveData<MyResult<List<HotSale>>> = MutableLiveData()
    val hotSalesResult: LiveData<MyResult<List<HotSale>>> get() = _hotSalesResult
    private var _bestSellersResult: MutableLiveData<MyResult<List<BestSeller>>> = MutableLiveData()
    val bestSellersResult: LiveData<MyResult<List<BestSeller>>> get() = _bestSellersResult

    init {
        fetchData()
    }


    private fun fetchData(){
        viewModelScope.launch(Dispatchers.IO){
            _allCategories.postValue(getAllCategoriesUseCase.invoke())
            _hotSalesResult.postValue(getHotSalesUseCase.invoke())
            _bestSellersResult.postValue(getBestSellersUseCase.invoke())
        }
    }

    fun requestCategoryById(id: Int){
        /*
        * Some code to request category
        * */

        /*
        * Mock implementation
        * */
        _allCategories.value?.let {
            val list = it.toMutableList()

            for (i in list.indices){
                if (list[i].isSelected){
                    list[i].apply {
                        val category = Category(id, name, image, isSelected = false)
                        list[i] = category
                    }
                } else if (list[i].id == id){
                    list[i].apply {
                        val category = Category(id, name, image, isSelected = true)
                        list[i] = category
                    }
                }
            }

            _allCategories.postValue(list)
        }
    }
}