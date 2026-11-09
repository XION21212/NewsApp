package com.example.newsapp.presentation.screen.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.NewsData
import com.example.newsapp.domain.repository.network.News

import com.example.newsapp.domain.usecase.AuthUseCase
import com.example.newsapp.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface FeedEvent{
    data class CategoryChange(val category: String): FeedEvent
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
   private val news: News
    ): ViewModel(){
    private val _state = MutableStateFlow(State())
    val state=_state.asStateFlow()
    private val _stateNews = MutableStateFlow(NewsData())
    val stateNews=_stateNews.asStateFlow()

    init {
        get()

    }

    fun onEvent(event: FeedEvent){
        when(event){
            is FeedEvent.CategoryChange ->{_state.update { it.copy(category = event.category) }}
        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun normalUrl(url: String?): String{
    if (url.isNullOrEmpty())
        return "Ошибка загрузки"
        return when{
            url.startsWith("http://")->url.replaceFirst("http://","https://")
            url.startsWith("https://")->url
            else->"Ошибка загрузки"
        }
    }

    fun getNews(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            try {
                val result = news.getNews(
                    category = _state.value.category
                )
                _stateNews.update { it.copy(articles = result.articles, totalArticles = result.totalArticles) }

            } catch (e: Exception) {
                android.util.Log.e("NEWS_DEBUG", "Ошибка загрузки новостей", e)
            }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun get()=viewModelScope.launch {
        val result= authUseCase.get()
        result?.let {of->
          _state.update { it->
              it.copy(entity = of)
          }
            }
        }

}