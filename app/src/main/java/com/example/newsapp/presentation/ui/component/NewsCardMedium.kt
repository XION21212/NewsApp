package com.example.newsapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.newsapp.domain.model.Articles


@Composable
fun NewsCardMedium(
    newsState: Articles,
    isLoading: Boolean,
    onClick: (ar:Articles)-> Unit={},
    writer: String="Reuters",

) {
    if (isLoading) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(190.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ).clickable{
                    onClick(newsState)
                }
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Gray,
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .height(100.dp),
                contentScale = ContentScale.FillBounds,
                model = newsState.image,
                contentDescription = ""
            )

            Column() {
                Text(
                    text = newsState.title,
                    lineHeight = 10.sp,
                    maxLines = 3,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(10.dp),
                    fontWeight = FontWeight.Bold

                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = writer,
                        fontSize = 10.sp
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