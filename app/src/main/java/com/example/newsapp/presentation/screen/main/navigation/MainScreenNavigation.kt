package com.example.newsapp.presentation.screen.main.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.screen.main.feed.FeedScreen
import com.example.newsapp.presentation.screen.main.profile.Profile


@Composable
fun MainScreenNavigation(){
    val navController= rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        NavHost(
            modifier = Modifier
                .padding(it),
            navController = navController,
            startDestination = MainScreenRoute.Feed
        ) {
            composable <MainScreenRoute.Feed>{
                FeedScreen()
            }
            composable<MainScreenRoute.Profile> {
                Profile()
            }
        }
    }
}