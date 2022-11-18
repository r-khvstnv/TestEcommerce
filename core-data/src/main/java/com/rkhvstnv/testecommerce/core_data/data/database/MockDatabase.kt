package com.rkhvstnv.testecommerce.core_data.data.database

import android.content.Context
import android.content.SharedPreferences
import com.rkhvstnv.testecommerce.core_data.R
import com.rkhvstnv.testecommerce.core_data.data.models.CategoryDto
import com.rkhvstnv.testecommerce.core_data.data.utils.DatabaseConstants
import javax.inject.Inject

/**
 * MockDatabase which is satisfied assigned task.
 *
 * All data is recorded to [SharedPreferences]. In production/release version will be used SQL database
 * wrapped by Room.
 * */
internal class MockDatabase @Inject constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(DatabaseConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)

    /**
     * Method record Json [String] to [SharedPreferences]
     * */
    fun insertAllProductInCart(json: String){
        val editor = sharedPreferences.edit()
        editor.putString(DatabaseConstants.PRODUCTS_IN_CART_KEY, json)
        editor.apply()
    }

    /**
     * Method returns Json [String] from [SharedPreferences].
     * If [String] doesn't exist, will be returned empty [String].
     * */
    fun getAllProductsInCart(): String = sharedPreferences.getString(DatabaseConstants.PRODUCTS_IN_CART_KEY, "") ?: ""

    /**
     * Method returns mock categories as list of [CategoryDto].
     * */
    fun getAllCategories(): List<CategoryDto>{
        val cat1 = CategoryDto(
            id = 1,
            name = "Phone",
            image = R.drawable.ic_phone
        )
        val cat2 = CategoryDto(
            id = 2,
            name = "Computer",
            image = R.drawable.ic_computer
        )
        val cat3 = CategoryDto(
            id = 3,
            name = "Health",
            image = R.drawable.ic_health
        )
        val cat4 = CategoryDto(
            id = 4,
            name = "Books",
            image = R.drawable.ic_book
        )
        val catN = CategoryDto(
            id = 5,
            name = "CatN",
            image = R.drawable.ic_phone
        )

        return listOf(
            cat1, cat2, cat3, cat4, catN
        )
    }
}