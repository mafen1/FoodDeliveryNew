package com.example.fooddelivery.core

sealed class ApiState {
    object ApiEmpty : ApiState()
    object Loading : ApiState()
    class Success<T>(val data: T) : ApiState()
    class Error(val message: String) : ApiState()
}