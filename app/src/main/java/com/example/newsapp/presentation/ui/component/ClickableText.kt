package com.example.newsapp.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.newsapp.R


@Composable
fun ClickableText(
    onClick:()-> Unit
){
    Text(
        text = "Все",
        modifier = Modifier
            .clickable {
                onClick()
            },
        fontSize = 15.sp,
        color = colorResource(R.color.teal_200)
    )
}