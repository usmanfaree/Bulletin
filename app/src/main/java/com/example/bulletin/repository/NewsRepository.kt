package com.example.bulletin.repository

import com.example.bulletin.api.NewsService
import com.example.bulletin.model.NewsResponse
import com.example.bulletin.utils.UiState

class NewsRepository(private val apiService: NewsService) {


    suspend fun getBreakingNews(category: String, apiKey: String): UiState<NewsResponse> {
        return try {
            val response = apiService.getBreakingNews(category, "en", apiKey)

            if (response.isSuccessful && response.body() != null) {
                UiState.Success(response.body()!!)
            } else {
                UiState.Error("Server Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            UiState.Error("Failure: ${e.localizedMessage ?: "Unknown Error"}")
        }
    }
}