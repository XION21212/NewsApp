package com.example.newsapp.presentation.screen.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.tooling.ComposeToolingApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.example.newsapp.presentation.navigation.Screen


@Composable
fun Profile(){

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "ScreenProfile",
            fontSize = 25.sp
        )
    }
}