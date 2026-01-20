package com.sankranthivinod.newsapp.ui

import com.sankranthivinod.newsapp.data.dto.Article

sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val news:List<Article>): NewsUiState()
    data class Error(val message:String): NewsUiState()
}
