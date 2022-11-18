package com.rkhvstnv.testecommerce.core_data.data.network

import com.rkhvstnv.testecommerce.core_data.data.utils.NetworkConst
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [RetrofitContainer] helps not to disclose [Retrofit] to other modules and keep all it's dependencies inside
 * this module only.
 * */
internal object RetrofitContainer {
    fun get(): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConst.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}