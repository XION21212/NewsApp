package com.example.newsapp.presentation.screen.main.feed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.domain.model.Articles
import com.example.newsapp.presentation.screen.main.FeedEvent
import com.example.newsapp.presentation.screen.main.MainViewModel
import com.example.newsapp.presentation.ui.component.NewsCardLarge
import com.example.newsapp.presentation.ui.component.NewsCardMedium

@Composable
fun FeedScreen(){
    ViewMain()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewMain(viewModel: MainViewModel= hiltViewModel()) {

    val state = viewModel.state.collectAsState()
    val newsState = viewModel.stateNews.collectAsState()


    val category = mapOf(
        "Все" to "general",
        "🔥 Тренды" to "entertainment",
        "Политика" to "world",
        "Спорт" to "sports",
        "IT" to "technology"
    )
    var selected by remember { mutableStateOf("entertainment") }
    val scrol = rememberScrollState(0)
    val clikedNews = remember { mutableStateOf(Articles()) }
    var showSheet by remember { mutableStateOf(false) }


    LaunchedEffect(selected) {
        viewModel.getNews()
    }

    Column(
        modifier = Modifier
            .background(colorResource(R.color.gray100))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(20.dp))

        ) {
            Column(
                Modifier
                    .padding(20.dp)
                    .background(Color.White, RoundedCornerShape(20.dp))

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row() {
                        Text(
                            text = "Ново",
                            fontWeight = FontWeight.Black,
                            fontSize = 25.sp
                        )
                        Text(
                            text = "сти",
                            fontWeight = FontWeight.Black,
                            color = colorResource(R.color.teal_200),
                            fontSize = 25.sp
                        )
                    }
                    Card(
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .size(54.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(R.color.teal_200)
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = state.value.entity?.let {
                                    it.userName[0].toString()
                                } ?: "",
                                color = Color.White,
                                fontSize = 29.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(18.dp),
                    placeholder = {
                        Text(
                            text = "Поиск новостей...",
                            color = colorResource(R.color.teal_200)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp),
                            tint = colorResource(R.color.teal_200)
                        )
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.Transparent,
                        focusedContainerColor = Color.Gray.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.Gray.copy(alpha = 0.4f),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    )
                )
                LazyRow(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(items = category.entries.toList()) { category ->
                        val isSelected = category.value == selected


                        Card(
                            border = BorderStroke(
                                color = Color.Gray,
                                width = if (isSelected) 0.dp else 1.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) colorResource(R.color.teal_200) else Color.White
                            ),
                            onClick = {
                                selected = category.value
                                viewModel.onEvent(FeedEvent.CategoryChange(selected))
                            }
                        ) {
                            Text(
                                text = category.key,
                                fontSize = 15.sp,
                                color = if (isSelected) Color.White else Color.Gray,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
        }

            when(selected){
                in "entertainment" -> {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .background(
                                color = colorResource(R.color.gray100)
                            )
                            .fillMaxSize()
                            .verticalScroll(scrol)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Главное",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )

                        }

                    if (!newsState.value.articles.isNullOrEmpty()) {
                        NewsCardLarge(
                            newsState = newsState.value.articles[0],
                            isLoading = state.value.isLoading,
                            onClick = {
                                clikedNews.value=it
                                showSheet=true
                            }
                        )
                    }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Читают сейчас",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )


                        }
                        if (!newsState.value.articles.isNullOrEmpty()) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(items = newsState.value.articles!!.drop(1).take(3)) {
                                    NewsCardMedium(
                                        newsState =it,
                                        isLoading = state.value.isLoading,
                                        onClick = {
                                            clikedNews.value=it
                                            showSheet=true
                                        }
                                    )
                                }
                            }
                        }


                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(20.dp)
                            .background(
                                color = colorResource(R.color.gray100)
                            )
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        items(items = newsState.value.articles) {
                            it?.let {
                                NewsCardLarge(
                                    newsState =it,
                                    isLoading = state.value.isLoading,
                                    onClick = {
                                        clikedNews.value=it
                                        showSheet=true
                                    }
                                )
                            }
                        }
                    }
                }
            }


    }
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet=false },
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
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
                    model = clikedNews.value.image,
                    contentDescription = "",
                )
                Text(
                    text = clikedNews.value.description,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = clikedNews.value.publishedAt,
                    fontSize = 10.sp
                )
            }
        }
    }
    }









