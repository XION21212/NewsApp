package com.example.newsapp.domain.util

import androidx.compose.ui.graphics.painter.Painter
import com.example.newsapp.presentation.screen.main.navigation.MainScreenRoute

data class BottomBarItem(
    val icon: Painter,
    val text: String,
    val route: MainScreenRoute
)
