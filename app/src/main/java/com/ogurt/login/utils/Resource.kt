package com.ogurt.login.utils

sealed class Resource<T>(val data: T? = null, val message: Int? = null) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: Int, data: T? = null) : Resource<T>(data, message)
}
