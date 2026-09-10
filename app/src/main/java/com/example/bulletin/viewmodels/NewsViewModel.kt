

package com.example.bulletin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulletin.model.NewsResponse
import com.example.bulletin.repository.NewsRepository
import com.example.bulletin.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel // 👈 Import HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject // 👈 Import Inject

@HiltViewModel // 👈 1. Hilt ko batata hai ke yeh ViewModel hai
class NewsViewModel @Inject constructor( // 👈 2. Repository auto-inject karne ke liye
    private val repository: NewsRepository
) : ViewModel() {

    val newsData = MutableLiveData<UiState<NewsResponse>>()

    fun getNews(category: String, apiKey: String) {
        newsData.postValue(UiState.Loading)

        viewModelScope.launch {
            val result = repository.getBreakingNews(category, apiKey)
            newsData.postValue(result)
        }
    }
}

