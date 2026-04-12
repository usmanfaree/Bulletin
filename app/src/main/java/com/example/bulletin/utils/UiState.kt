package com.example.bulletin.utils

sealed class UiState<out T> {
    // Jab data load ho raha ho
    object Loading : UiState<Nothing>()

    // Jab data kamyabi se mil jaye
    data class Success<T>(val data: T) : UiState<T>()

    // Jab koi error aaye (Internet ya Server ka)
    data class Error(val message: String) : UiState<Nothing>()
}