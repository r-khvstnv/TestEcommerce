package com.rkhvstnv.testecommerce.core_data.domain.usecase

import android.util.Log
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCart
import javax.inject.Inject

class GetAllProductsInCartUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): MyResult<List<ProductInCart>>{
        when(val dtoResult = repository.getProductsInCart()){

            is MyResult.Error -> return MyResult.Error(dtoResult.code, dtoResult.message)
            is MyResult.Exception -> return MyResult.Error(1, "")
            is MyResult.Success -> {

                Log.i("dtoResult1", dtoResult.data.toString())

                val productInCartList = arrayListOf<ProductInCart>()
                val productInCartDtoList = dtoResult.data
                for (item in productInCartDtoList){

                    when(val phoneResult = repository.getPhoneById(item.id)){
                        is MyResult.Error -> return MyResult.Error(phoneResult.code, phoneResult.message)
                        is MyResult.Exception -> return MyResult.Exception(e = phoneResult.e)
                        is MyResult.Success -> {
                            val productInCart = ProductInCart(
                                id = item.id,
                                amount = item.amount,
                                name = phoneResult.data.title,
                                price = phoneResult.data.price,
                                image = phoneResult.data.images[0]
                            )
                            productInCartList.add(productInCart)
                            Log.i("dtoResult_inter", productInCartList.toString())
                        }
                    }

                }

                Log.i("dtoResult2", productInCartList.toString())
                return MyResult.Success(data = productInCartList)
            }
        }
    }
}