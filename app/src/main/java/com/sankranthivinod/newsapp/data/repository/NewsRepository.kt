package com.sankranthivinod.newsapp.data.repository

import com.sankranthivinod.newsapp.data.api.NewsApiService
import com.sankranthivinod.newsapp.data.dto.NewsResponse
import retrofit2.HttpException

class NewsRepository(private val api: NewsApiService) {

    suspend fun getTopHeadlines(country: String): NewsResponse =
        api.getTopHeadlines(country).let { response ->
            if (response.isSuccessful) {
                response.body()
                    ?: throw IllegalStateException("Response body is null")
            } else {
                throw HttpException(response)
            }
        }

}