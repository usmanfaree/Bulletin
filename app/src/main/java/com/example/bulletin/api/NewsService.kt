package com.example.bulletin.api

import com.example.bulletin.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getNews(
        @Query("category") category: String = "general",
        @Query("lang") lang: String = "en",
        @Query("apikey") apiKey: String
    ): Call<NewsResponse>
}