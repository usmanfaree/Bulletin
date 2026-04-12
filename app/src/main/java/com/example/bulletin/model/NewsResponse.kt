package com.example.bulletin.model

// 1. Main Response Class
data class NewsResponse(
    val totalArticles: Int,
    val articles: List<Article>
)

// 2. Article Class (Make sure it's outside or correctly nested)
data class Article(
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val image: String,
    val publishedAt: String
)