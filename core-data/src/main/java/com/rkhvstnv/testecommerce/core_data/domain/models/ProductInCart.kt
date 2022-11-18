package com.rkhvstnv.testecommerce.core_data.domain.models

import java.util.UUID

class ProductInCart(
    val id: Int,
    amount: Int,
    val name: String,
    val price: Int,
    val image: String,
    /**
     * Value changes automatically using [amount][com.rkhvstnv.testecommerce.core_data.domain.models.ProductInCart.amount]
     * */
    var totalCost: Int = amount * price
){

    /**
     * Value has custom set method. Before field will be assigned,
     * [totalCost][com.rkhvstnv.testecommerce.core_data.domain.models.ProductInCart.totalCost]
     * is estimated.
     * */
    var amount: Int = amount
    set(value) {
        totalCost = value * price
        field = value
    }

    /**
     * This value __is necessary__ due to the specifics of the technical task.
     *
     * Specific Details:
     *
     * [List] of [ProductInCart] with all equals field should be displayed
     * in the RecyclerView. For efficient solving of this problem and futures as well are used [hashMock].
     *
     * */
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