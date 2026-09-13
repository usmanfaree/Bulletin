package com.example.bulletin.api

import com.example.bulletin.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v4/top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("lang") lang: String,
        @Query("apikey") apiKey: String
    ): Response<NewsResponse>
}