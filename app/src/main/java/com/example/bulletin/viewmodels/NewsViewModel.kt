package com.example.bulletin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulletin.model.Article
import com.example.bulletin.repository.NewsRepository
import com.example.bulletin.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {


    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Loading())
    val newsState: StateFlow<Resource<List<Article>>> = _newsState

    fun getNews(category: String, apiKey: String) {
        viewModelScope.launch {
            repository.getBreakingNews(category, apiKey).collect { resource ->
                _newsState.value = resource
            }
        }
    }
}