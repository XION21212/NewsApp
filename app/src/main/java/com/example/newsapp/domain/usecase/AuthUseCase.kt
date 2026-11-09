package com.example.newsapp.domain.usecase

import android.util.Patterns
import com.example.newsapp.domain.model.UserEntity
import com.example.newsapp.domain.repository.network.AuthUser
import com.example.newsapp.domain.util.Result
import javax.inject.Inject

class AuthUseCase @Inject constructor(val authUser: AuthUser){

    fun getUser(): String?{
       return authUser.userUid
    }
   suspend fun registerUseCase(email: String, password: String, name: String): Result{
        if (email.isEmpty() && password.isEmpty()){
            return Result.Error(msg = "Поля не могут быть пустыми!")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches())
            return Result.Error(msg = "Некоректно введен маил")

        if ( password.length<7)
            return Result.Error(msg = "Пароль не может быть меньше 7 символов")
        else
            return authUser.register(email.trim(),password.trim(),name.trim())
    }
    suspend fun loginUserCase(email: String,password: String): Result{
        if (email.isEmpty()||password.isEmpty()){
            return Result.Error(msg = "Поля не могут быть пустыми!")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches())
            return Result.Error(msg = "Некоректно введен маил")
        else
           return authUser.login(email,password)
    }
    suspend fun get(): UserEntity?{
      return authUser.getAuthInfo()
    }
}