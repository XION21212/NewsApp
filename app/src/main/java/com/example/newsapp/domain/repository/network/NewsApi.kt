package com.example.newsapp.domain.repository.network

import androidx.room.Query
import com.example.newsapp.domain.model.NewsData
import retrofit2.http.GET

interface NewsApi {
    @GET("api/v4/top-headlines")
    suspend fun getNews(
        @retrofit2.http.Query("category") category: String,
        @retrofit2.http.Query("lang") lang: String="ru",
        @retrofit2.http.Query("max")max: Int,
        @retrofit2.http.Query("apikey") key: String="a09a9a03396376bd7bbb5582044b88e6"
    ): NewsData


}