package com.rkhvstnv.testecommerce.core_data.data.utils

import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import retrofit2.HttpException
import retrofit2.Response

/**
 * Unified extension for handling the [Response].
 *
 * A method is processing [Response] and returns it as [MyResult].
 * */
internal suspend fun <T: Any> handleApi(
    execute: suspend () -> Response<T>
): MyResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null){
            MyResult.Success(data = body)
        } else{
            MyResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException){
        MyResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable){
        MyResult.Exception(e = e)
    }
}