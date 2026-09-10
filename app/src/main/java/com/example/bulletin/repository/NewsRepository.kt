package com.example.bulletin.repository

import androidx.lifecycle.LiveData
import com.example.bulletin.api.NewsService
import com.example.bulletin.db.ArticleDao
import com.example.bulletin.model.Article
import com.example.bulletin.model.NewsResponse
import com.example.bulletin.utils.UiState
import javax.inject.Inject // 👈 1. YEH IMPORT ZAROORI HAI

// 👈 2. SIRF YAHAN @Inject constructor LAGAEN
class NewsRepository @Inject constructor(
    private val apiService: NewsService,
    private val dao: ArticleDao
) {

    // 👇 AAP KA POORA LOGIC SAME WAISA HI RAHEGA (KOI CHANGE NAHI)
    suspend fun getBreakingNews(category: String, apiKey: String): UiState<NewsResponse> {
        return try {
            val response = apiService.getBreakingNews(category, "en", apiKey)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                insertArticles(body.articles)
                UiState.Success(body)
            } else {
                val savedData = dao.getArticlesOnce()
                if (savedData.isNotEmpty()) {
                    UiState.CachedData(savedData)
                } else {
                    UiState.Error("Server Error: ${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            val roomData = dao.getArticlesOnce()
            if (roomData.isNotEmpty()) {
                UiState.CachedData(roomData)
            } else {
                UiState.Error("Failure: ${e.localizedMessage ?: "Unknown Error"}")
            }
        }
    }

    suspend fun insertArticles(article: List<Article>) {
        dao.insert(article)
    }

    fun getSavedArticles(): LiveData<List<Article>> {
        return dao.getArticle()
    }
}