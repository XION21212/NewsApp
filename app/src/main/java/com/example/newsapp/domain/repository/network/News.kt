package com.example.newsapp.domain.repository.network

import com.example.newsapp.domain.model.Articles
import com.example.newsapp.domain.model.NewsData
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import javax.inject.Inject

class News @Inject constructor(
    retrofit: Retrofit
) {
    private val api=retrofit.create(NewsApi::class.java)

    suspend fun getNews(category: String): NewsData{
        return try {
            CoroutineScope(Dispatchers.IO).async {
                api.getNews(
                    category = category,
                    max = 10
                )
            }.await()

        }catch (e: CancellationException) {
            throw e
        }catch (e: Exception){
            NewsData(articles =listOf( Articles(title = "$e")))
        }
    }

}