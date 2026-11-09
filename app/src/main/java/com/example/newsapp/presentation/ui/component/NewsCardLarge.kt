package com.example.newsapp.presentation.ui.component

import android.R.attr.onClick
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.newsapp.domain.model.Articles
import com.example.newsapp.domain.model.NewsData


@Composable
fun NewsCardLarge(
    newsState: Articles,
    isLoading: Boolean,
    onClick: (ar:Articles)-> Unit={}
) {
    if (isLoading ) {
        Column(
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier= Modifier
                    .size(48.dp)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .clickable{
                    onClick(newsState)
                }
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Gray,
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .clip(
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .height(150.dp),
                contentScale = ContentScale.FillBounds,
                model = newsState.image,
                contentDescription = "",
            )

            Column() {
                Text(
                    text =newsState.title ,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(10.dp),
                    fontWeight = FontWeight.Bold

                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = newsState.publishedAt
                    )
                    Spacer(
                        modifier = Modifier
                            .size(10.dp)
                    )

                }
            }
        }
    }
}