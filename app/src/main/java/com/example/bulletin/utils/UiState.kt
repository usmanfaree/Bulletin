package com.example.bulletin.utils

import com.example.bulletin.model.Article

sealed class UiState<out T> {
    // Jab data load ho raha ho
    object Loading : UiState<Nothing>()

    // Jab data kamyabi se mil jaye
    data class Success<T>(val data: T) : UiState<T>()

    data class CachedData(val articles: List<Article>) : UiState<Nothing>()
    data class Error(val message: String) : UiState<Nothing>()
}