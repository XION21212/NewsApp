package com.example.newsapp.presentation.state

import com.example.newsapp.domain.model.NewsData
import com.example.newsapp.domain.model.UserEntity
import com.example.newsapp.domain.util.Result

data class State(
    val email: String="",
    val password: String="",
    val name: String="",
    var category: String="entertainment",
    var result: Result?=null,
    val entity: UserEntity?=null,
    var isLoading: Boolean=false,
)
