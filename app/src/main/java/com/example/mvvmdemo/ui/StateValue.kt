package com.example.mvvmdemo.ui

sealed class StateValue<out T> {
    object Idle : StateValue<Nothing>()
    object Loading : StateValue<Nothing>()
    data class Success<T>(val value: T?) : StateValue<T>()
    data class Failure<T>(val throwable: Throwable?) : StateValue<T>()
//    operator fun invoke() = (this as? Success)?.value
}