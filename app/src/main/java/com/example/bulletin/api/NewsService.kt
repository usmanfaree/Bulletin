package com.example.bulletin.api

import com.example.bulletin.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("lang") language: String,
        @Query("apikey") token: String
    ): Response<NewsResponse>


}