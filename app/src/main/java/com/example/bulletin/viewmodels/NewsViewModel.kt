package com.example.bulletin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulletin.model.NewsResponse
import com.example.bulletin.repository.NewsRepository
import com.example.bulletin.utils.UiState
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel()
{



    val newsData = MutableLiveData<UiState<NewsResponse>>()


    fun getNews(category: String, apiKey: String)
    {

        newsData.postValue(UiState.Loading)//to show loading bar on screen

        viewModelScope.launch{
            val result = repository.getBreakingNews(category, apiKey)
            newsData.postValue(result)



        }
        }




    }//end of function getNews






