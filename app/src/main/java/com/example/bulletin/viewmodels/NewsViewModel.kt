package com.example.bulletin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulletin.model.NewsResponse

import com.example.bulletin.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {


    val newsData: MutableLiveData<Response<NewsResponse>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchNews(country: String, apiKey: String) {
        // Coroutine shuru ho gayi!
        viewModelScope.launch {
            isLoading.postValue(true) // Loading shuru

            try {
                val response = repository.getNews(country, apiKey)
                newsData.postValue(response) // Data ya Error yahan save ho jayega
            } catch (e: Exception) {
                // Agar internet band hai toh app crash nahi hogi
            } finally {
                isLoading.postValue(false) // Loading khatam
            }
        }
    }
}