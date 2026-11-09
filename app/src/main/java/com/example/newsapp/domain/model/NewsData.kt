package com.example.newsapp.domain.model

data class NewsData(
    var totalArticles: Int? =0,
    var articles: List<Articles> = emptyList()
)

data class Articles(
    var id: String="",
    var title: String="",
    var description: String="",
    var content: String="",
    var image: String="",
    var publishedAt: String=""
)
