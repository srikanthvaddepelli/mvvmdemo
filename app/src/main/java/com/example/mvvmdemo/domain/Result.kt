package com.example.mvvmdemo.domain

sealed class Result<T>(val data: T? = null, val throwable: Throwable? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: Throwable) : Result<T>(throwable = message)
}