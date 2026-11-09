package com.example.newsapp.domain.model

import androidx.navigation.Navigator
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo

data class UserEntity(
    val id: String="",
    val email: String="",
    val userName: String="",
)
