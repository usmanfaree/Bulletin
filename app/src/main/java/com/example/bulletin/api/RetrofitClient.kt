package com.example.bulletin.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetrofitClient {


    object RetrofitClient {
        private const val BASE_URL = "https://gnews.io/api/v4/"

        val instance: NewsService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // JSON ko Kotlin mein badalne ke liye
                .build()

            retrofit.create(NewsService::class.java)
        }
    }
}