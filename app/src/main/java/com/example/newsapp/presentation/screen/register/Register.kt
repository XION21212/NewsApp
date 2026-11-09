package com.example.newsapp.presentation.screen.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.domain.util.Result
import com.example.newsapp.presentation.navigation.Screen

@Composable
fun Register(
    viewModel: RegisterViewModel= hiltViewModel(),
    navigateTo:(Screen)-> Unit
) {
    var visibilityPassword by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val state by viewModel._state.collectAsState()
    var blur=if (state.isLoading) 10.dp else 0.dp

    LaunchedEffect(state.result) {
        state.result?.let {
            when (it) {
                is Result.Error -> Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                is Result.Succes -> navigateTo(Screen.Auth)
            }
        }
    }
    Box() {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxSize()
                .background(
                    Color.White
                )
                .blur(blur),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Image(
                painter = painterResource(R.drawable.newspaper_svgrepo_com_2_),
                contentDescription = "",
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.teal_200),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(124.dp)
                    .padding(10.dp)

            )
            Row(
                modifier = Modifier
                    .padding(
                        top = 20.dp
                    )

                    .fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text = "Создать аккаунт",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "Заполните данные для регистрации",
                        color = Color.Gray,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(
                                top = 5.dp
                            )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(
                        top = 20.dp
                    )
            ) {

                Text(
                    text = "Имя",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        viewModel.onEvent(ChangeRegisterScreen.EventName(it.take(12)))
                    },
                    shape = RoundedCornerShape(15.dp),
                    label = {
                        Text(
                            text = "Александр",
                            color = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.Transparent,
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Email",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(
                            top = 10.dp
                        )
                )
                OutlinedTextField(
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(ChangeRegisterScreen.EventEmail(it.take(20)))
                    },
                    maxLines = 1,
                    shape = RoundedCornerShape(15.dp),
                    label = {
                        Text(
                            text = "you@example.com",
                            color = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.Transparent,
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Пароль",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(
                            top = 10.dp
                        )
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(ChangeRegisterScreen.EventPassword(it.take(20)))
                    },
                    maxLines = 1,
                    visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.eye),
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    visibilityPassword = !visibilityPassword
                                }
                                .size(25.dp)
                        )
                    },
                    shape = RoundedCornerShape(15.dp),
                    label = {
                        Text(
                            text = "Минимум 6 символов",
                            color = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.Transparent,
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.register()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.teal_200)
                    ),
                    modifier = Modifier
                        .padding(
                            top = 20.dp
                        )
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp)

                ) {
                    Text(
                        text = "Создать аккаунт",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,

                        )
                }


            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            Row(
                modifier = Modifier
                    .weight(0.1f)
            ) {
                Text(
                    text = "Уже есть аккаунта?",
                    color = Color.Gray,
                    fontSize = 15.sp
                )
                Text(
                    text = " Войти",
                    color = colorResource(R.color.teal_200),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .clickable {
                            navigateTo(Screen.Auth)
                        }
                )
            }
        }
         if (state.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(44.dp)
                )
            }
        }
    }
}


