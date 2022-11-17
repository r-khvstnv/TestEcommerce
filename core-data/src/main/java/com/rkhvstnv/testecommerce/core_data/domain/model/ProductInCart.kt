package com.rkhvstnv.testecommerce.core_data.domain.model

import java.util.UUID

class ProductInCart(
    val id: Int,
    amount: Int,
    val name: String,
    val price: Int,
    val image: String,
    var totalCost: Int = amount * price
){
    var amount: Int = amount
    set(value) {
        totalCost = value * price
        field = value
    }

    private val hashMock: String = UUID.randomUUID().toString()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductInCart

        return (amount - other.amount) == 0
                && hashMock == other.hashMock
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + price
        result = 31 * result + image.hashCode()
        result = 31 * result + totalCost
        result = 31 * result + amount
        return result
    }
}