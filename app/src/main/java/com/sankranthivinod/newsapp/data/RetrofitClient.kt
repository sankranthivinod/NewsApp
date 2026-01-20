package com.sankranthivinod.newsapp.data

import com.sankranthivinod.newsapp.data.api.NewsApiService
import com.sankranthivinod.newsapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor {
        chain ->
        val request = chain.request().newBuilder().
                        addHeader("Authorization", Constants.API_KEY).build()
        chain.proceed(request)
    }.build()

    val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val newsApi = retrofit.create(NewsApiService::class.java)

    /*val api: NewsApiService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApiService::class.java)*/
}