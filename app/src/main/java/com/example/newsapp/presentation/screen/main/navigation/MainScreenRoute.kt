package com.example.newsapp.presentation.screen.main.navigation

import kotlinx.serialization.Serializable


sealed interface MainScreenRoute {
    @Serializable
    data object Feed: MainScreenRoute
    @Serializable
    data object Profile: MainScreenRoute
}