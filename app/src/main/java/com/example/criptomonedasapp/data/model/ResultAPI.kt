package com.example.criptomonedasapp.data.model

sealed class ResultAPI<T> {
    data class Error<T>(val message: String, val code: Int): ResultAPI<T>()
    data class Success<T>(val data: T): ResultAPI<T>()
}