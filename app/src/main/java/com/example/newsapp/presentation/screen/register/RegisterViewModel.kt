package com.example.newsapp.presentation.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.AuthUseCase
import com.example.newsapp.domain.util.Result
import com.example.newsapp.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

sealed class ChangeRegisterScreen(){
    data class EventName(val name: String): ChangeRegisterScreen()
    data class EventEmail(val email: String):ChangeRegisterScreen()
    data class EventPassword(val password: String): ChangeRegisterScreen()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
   val authUseCase: AuthUseCase
): ViewModel() {

    private val state = MutableStateFlow(State())
    var _state=state.asStateFlow()
        private set



    fun onEvent(event: ChangeRegisterScreen){
        when(event){
            is ChangeRegisterScreen.EventEmail ->  state.update { it.copy(email = event.email) }
            is ChangeRegisterScreen.EventPassword -> state.update { it.copy(password = event.password) }
            is ChangeRegisterScreen.EventName ->  state.update { it.copy(name = event.name) }
        }
    }
   fun register() {
       viewModelScope.launch {
           state.update { it.copy(isLoading = true) }
           delay(1000)
               val result = authUseCase.registerUseCase(
                   state.value.email,
                   state.value.password,
                   state.value.name
               )
           state.update { it.copy(result = result, isLoading = false) }
       }
   }
}