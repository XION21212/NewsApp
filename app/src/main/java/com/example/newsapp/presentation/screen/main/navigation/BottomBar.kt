package com.example.newsapp.presentation.screen.main.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.domain.util.BottomBarItem

@Composable
fun BottomBar(
    navController: NavController
){
    var selected by remember{mutableStateOf(0)}
    val items=listOf(
        BottomBarItem(
            icon = painterResource(R.drawable.house),
            text = "Главная",
            route = MainScreenRoute.Feed
        ),
        BottomBarItem(
            icon = painterResource(R.drawable.profile),
            text = "Профиль",
            route = MainScreenRoute.Profile
        )

    )

    NavigationBar(
        containerColor = Color.Gray,
        modifier = Modifier
            .height(84.dp),
    ) {
        items.forEachIndexed { index, item ->
            val select=selected==index
            NavigationBarItem(
                selected =select,
                onClick = {
                    selected=index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = "",
                        tint = if (select) colorResource(R.color.teal_200) else Color.White,
                        modifier = Modifier
                            .size(44.dp)
                    )
                },

            )
        }
    }
}