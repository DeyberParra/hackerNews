package com.deyber.database.data


sealed class DBResource<out T> {
    class Success<out T>(val data: T) : DBResource<T>()
    class Error<out T>(val throwable: Throwable) : DBResource<T>()
}

inline fun <reified T> DBResource<T>.doSuccess(callback: (data: T) -> Unit) {
    if (this is DBResource.Success) {
        callback(data)
    }
}

inline fun <reified T> DBResource<T>.doError(callback: (throwable: Throwable) -> Unit) {
    if (this is DBResource.Error) {
        callback(throwable)
    }
}
