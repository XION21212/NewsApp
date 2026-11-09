package com.example.newsapp.presentation.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.trace
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.domain.util.Result
import com.example.newsapp.presentation.navigation.Screen

@Composable
fun Login(
    navigateTO:(screen: Screen)-> Unit={},
    viewModel: LoginViewModel= hiltViewModel()
){
    var visibilityPassword by remember {mutableStateOf(false)}
    val state by viewModel._state.collectAsState()
    val context= LocalContext.current
    LaunchedEffect(state.result) {
        state.result?.let {
            when (it) {
                is Result.Error -> Toast.makeText(context,"Ошибка $it", Toast.LENGTH_SHORT).show()
                is Result.Succes -> navigateTO(Screen.Main)
            }
        }
    }
    Box() {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .background(
                    Color.White
                )
                .blur(if (state.isLoading) 10.dp else 0.dp),
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
                        text = "С возвращением",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "Войдите, чтобы читать новости",
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
                    text = "Email",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                OutlinedTextField(
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(ChangeLoginScreen.EventEmail(it))
                    },
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
                        viewModel.onEvent(ChangeLoginScreen.EventPassword(it))
                    },
                    visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(15.dp),
                    label = {
                        Text(
                            text = "**********",
                            color = Color.Gray
                        )
                    },
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
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.Transparent,
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Забыли пароль?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(R.color.teal_200),
                        modifier = Modifier
                            .padding(
                                top = 20.dp
                            )
                    )
                }
                Button(
                    onClick = {
                        viewModel.login()
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
                        text = "Войти",
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
                    text = "Нет аккаунта?",
                    color = Color.Gray,
                    fontSize = 15.sp
                )
                Text(
                    text = "Зарегистрироваться",
                    color = colorResource(R.color.teal_200),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .clickable {
                            navigateTO(Screen.Register)
                        }
                )
            }
        }
        if (state.isLoading){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(44.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun prevLogin(){
    Login()
}