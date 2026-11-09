package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.presentation.screen.login.Login
import com.example.newsapp.presentation.screen.login.LoginViewModel
import com.example.newsapp.presentation.screen.main.navigation.MainScreenNavigation
import com.example.newsapp.presentation.screen.register.Register
import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import javax.inject.Inject


sealed class Screen{
    @Serializable
    data object Auth: Screen()
    @Serializable
    data object Register: Screen()
    @Serializable
    data object Main: Screen()

}

@Composable
fun MainNavigation (
    navController: NavHostController,
    loginViewModel:LoginViewModel= hiltViewModel()
){
    NavHost(
        startDestination = if (loginViewModel.user==null) Screen.Auth else Screen.Main,
        navController = navController,
    ){
        composable <Screen.Register> {
            Register(
                navigateTo = {
                    navController.navigate(it)
                }
            )
        }
        composable <Screen.Auth> {
            Login(
                navigateTO = {
                    navController.navigate(it)
                }
            )


        }
        composable <Screen.Main> {
            MainScreenNavigation()
        }

    }
}