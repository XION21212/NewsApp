package com.example.newsapp.domain.repository.network

import com.example.newsapp.domain.model.UserEntity
import com.example.newsapp.domain.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthUser @Inject constructor(
    val fireAuth: FirebaseAuth,
    val fireStore: FirebaseFirestore
) {
    val userUid=fireAuth.currentUser?.uid

    suspend fun register(email: String, password: String, name: String): Result{
        return try {
            val result=fireAuth
                .createUserWithEmailAndPassword(email,password)
                .await()

            val user=result.user ?: return Result.Error(msg = "Ошибка создание пользователя!")
            fireStore
                .collection("users")
                .document(user.uid)
                .set(UserEntity(
                    id = user.uid,
                    email = email,
                    userName = name,
                ))
                .await()
                Result.Succes("Успешно создан")

        }catch (e: Exception){
            Result.Error(msg = "Ошибка регистрации:$e")
        }
    }

    suspend fun login(email: String,password: String): Result{
        return try {
            fireAuth
                .signInWithEmailAndPassword(email,password)
                .await()
            Result.Succes("Добро пожаловать")
        }
        catch (e: FirebaseAuthWeakPasswordException){
            Result.Error("Неправильный пароль или маил")
        }
        catch (e: Exception){
            Result.Error("Ошибка $e")
        }
    }
    suspend fun getAuthInfo(): UserEntity? {
        return try {
            val snapshot=fireStore
                .collection("users")
                .document(userUid.toString())
                .get()
                .await()
            snapshot.toObject(UserEntity::class.java)
        }catch (e: Exception){
            null
        }
    }
}