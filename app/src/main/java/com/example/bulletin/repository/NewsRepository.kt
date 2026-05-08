package com.example.bulletin.repository

import androidx.lifecycle.LiveData
import com.example.bulletin.api.NewsService
import com.example.bulletin.db.ArticleDao
import com.example.bulletin.db.NewsDatabase
import com.example.bulletin.model.Article
import com.example.bulletin.model.NewsResponse
import com.example.bulletin.utils.UiState

class NewsRepository(private val apiService: NewsService,private val dao: ArticleDao)
{



    suspend fun getBreakingNews(category: String, apiKey: String): UiState<NewsResponse> {
        return try {
            val response = apiService.getBreakingNews(category, "en", apiKey)

            if (response.isSuccessful && response.body() != null)
            {

                val articles = response.body()!!.articles
                insertfun(articles)
                UiState.Success(response.body()!!)
            }
            else
            {
                UiState.Error("Server Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            UiState.Error("Failure: ${e.localizedMessage ?: "Unknown Error"}")
        }


    }

    suspend fun insertfun(article: List<Article>)
    {

    dao.insert(article)

    }
     fun getdata(): LiveData<List<Article>>
    {

       return dao.getArticle()
    }



}