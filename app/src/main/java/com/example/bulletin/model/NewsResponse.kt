package com.example.bulletin.model

class NewsResponse {
    data class NewsResponse(
        val articles: List<Article>
    )

    data class Article(
        val title: String,
        val description: String?,
        val url: String,
        val image: String?
    )
}