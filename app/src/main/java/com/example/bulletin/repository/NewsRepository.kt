package com.example.bulletin.repository

import com.example.bulletin.api.NewsService

class NewsRepository(private val newsService: NewsService) {
    // Ye function API se data layega
    suspend fun getNews(country: String, apiKey: String) =
        newsService.getNews(country, apiKey)
}