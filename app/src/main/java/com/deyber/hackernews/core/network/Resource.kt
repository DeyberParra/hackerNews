package com.deyber.hackernews.core.network

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val message: String?, val throwable: Throwable? , val typeErrorType: ResourceErrorType?=null) : Resource<T>()
}


inline fun <reified T> Resource<T>.doLoading(callback: () -> Unit) {
    if (this is Resource.Loading<*>) {
        callback()
    }
}

inline fun <reified T> Resource<T>.doSuccess(callback: (data: T) -> Unit) {
    if (this is Resource.Success) {
        callback(data)
    }
}

inline fun <reified T> Resource<T>.doFailure(
    callback: (
        error: String?,
        throwable: Throwable?,
        typeErrorType: ResourceErrorType?
    ) -> Unit,
) {
    if (this is Resource.Failure) {
        callback(message, throwable, typeErrorType)
    }
}

interface ResourceErrorType

