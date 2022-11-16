package com.rkhvstnv.testecommerce.core_data.data.database

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.rkhvstnv.testecommerce.core_data.data.utils.DatabaseConstants
import javax.inject.Inject

class MockDatabase @Inject constructor(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(DatabaseConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)

    fun insertAllProductInCart(json: String){
        val editor = sharedPreferences.edit()
        editor.putString(DatabaseConstants.PRODUCTS_IN_CART_KEY, json)
        editor.apply()
    }

    fun getAllProductsInCart(): String = sharedPreferences.getString(DatabaseConstants.PRODUCTS_IN_CART_KEY, "") ?: ""
}