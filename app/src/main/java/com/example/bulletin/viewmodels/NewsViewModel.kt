package com.example.bulletin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulletin.model.Article
import com.example.bulletin.model.NewsResponse
import com.example.bulletin.repository.NewsRepository
import com.example.bulletin.utils.UiState
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel()
{



    val newsData = MutableLiveData<UiState<NewsResponse>>()
    val articles: LiveData<List<Article>> = repository.getdata()

    fun getNews(category: String, apiKey: String)
    {

        newsData.postValue(UiState.Loading)//to show loading bar on screen

        viewModelScope.launch{
            val result = repository.getBreakingNews(category, apiKey)
            Log.d("VM_TEST", "Result: $result")
            newsData.postValue(result)


        }





    }




    }//end of function getNews






