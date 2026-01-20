package com.sankranthivinod.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sankranthivinod.newsapp.data.repository.NewsRepository
import com.sankranthivinod.newsapp.data.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel:ViewModel() {
    private val repository = NewsRepository(RetrofitClient.newsApi)
    private val _news = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val news: StateFlow<NewsUiState> = _news

    fun loadNews(){
        viewModelScope.launch(Dispatchers.Main) {
            _news.value = NewsUiState.Loading
            doBackgroundTask()
        }

    }

    suspend fun doBackgroundTask(){
        withContext(Dispatchers.IO){
            try{
                val response = repository.getTopHeadlines("us")
                withContext(Dispatchers.Main){
                    _news.value = NewsUiState.Success(response.articles)
                }
             }catch (e:Exception){
                withContext(Dispatchers.Main) {
                    _news.value = NewsUiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }
}