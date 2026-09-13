package com.example.bulletin.repository

import com.example.bulletin.api.NewsService
import com.example.bulletin.db.ArticleDao
import com.example.bulletin.model.Article
import com.example.bulletin.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: NewsService,
    private val dao: ArticleDao
) {

    fun getBreakingNews(category: String, apiKey: String): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.getBreakingNews(category, "en", apiKey)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                dao.insert(body.articles)
            } else {
                emit(Resource.Error("Server Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network Error: ${e.localizedMessage ?: "Unable to fetch online news"}"))
        }


        val dbStream = dao.getArticles().map { cachedArticles ->
            Resource.Success(cachedArticles)
        }

        emitAll(dbStream)
    }
}