package com.rkhvstnv.testecommerce.utils

interface Mapper<in I, out O> {
    fun map(input: I): O
}