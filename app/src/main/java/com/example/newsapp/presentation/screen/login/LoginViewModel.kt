package com.example.newsapp.presentation.screen.login

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.UserEntity
import com.example.newsapp.domain.usecase.AuthUseCase
import com.example.newsapp.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class ChangeLoginScreen(){
    data class EventEmail(val email: String): ChangeLoginScreen()
    data class EventPassword(val password: String): ChangeLoginScreen()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    val authUseCase: AuthUseCase
): ViewModel() {

    val user=authUseCase.getUser()
   private var state = MutableStateFlow(State())
    val _state=state.asStateFlow()

    fun onEvent(event: ChangeLoginScreen){
            when (event) {
                is ChangeLoginScreen.EventEmail -> state.update { it.copy(email = event.email) }
                is ChangeLoginScreen.EventPassword -> state.update{it.copy(password = event.password)}
        }

    }
    fun login()=viewModelScope.launch(Dispatchers.IO) {
        state.update { it.copy(isLoading = true) }
       val result= authUseCase.loginUserCase(state.value.email,state.value.password)
        state.update{it.copy(result = result, isLoading = false)}
    }


}