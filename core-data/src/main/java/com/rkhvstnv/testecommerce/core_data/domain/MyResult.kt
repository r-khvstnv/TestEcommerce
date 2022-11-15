package com.rkhvstnv.testecommerce.core_data.domain

sealed class MyResult<T: Any> {
    class Success<T: Any>(val data: T): MyResult<T>()
    class Error<T: Any>(val code: Int, val message: String): MyResult<T>()
    class Exception<T: Any>(val e: Throwable): MyResult<T>()
}