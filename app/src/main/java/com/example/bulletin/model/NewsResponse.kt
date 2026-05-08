package com.example.bulletin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// 1. Main Response Class
data class NewsResponse(
    val totalArticles: Int,
    val articles: List<Article>
)

// 2. Article Class (Make sure it's outside or correctly nested)
@Entity(tableName = "articles")
data class Article(

    @PrimaryKey
    val url: String,

    val title: String?,
    val description: String?,
    val content: String?,
    val urlToImage: String?,
    val publishedAt: String?
)