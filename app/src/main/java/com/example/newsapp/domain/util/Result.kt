package com.example.newsapp.domain.util

sealed interface Result{
    data class Succes(val msg: String): Result
    data class Error(val msg: String): Result
}