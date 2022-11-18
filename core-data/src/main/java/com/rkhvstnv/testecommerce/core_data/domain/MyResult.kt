package com.rkhvstnv.testecommerce.core_data.domain


/**
 * Sealed class that helps to collect complex data, where errors or exceptions may be received too.
 *
 * [MyResult] has as child:
 *
 * [Success] -> stores data [T];
 *
 * [Error] -> error code [Int] and message [String];
 *
 * [Exception] -> exception [Throwable].
 * */
sealed class MyResult<T: Any> {
    class Success<T: Any>(val data: T): MyResult<T>()
    class Error<T: Any>(val code: Int, val message: String): MyResult<T>()
    class Exception<T: Any>(val e: Throwable): MyResult<T>()
}