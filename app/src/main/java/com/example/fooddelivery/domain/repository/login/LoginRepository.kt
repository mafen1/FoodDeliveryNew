package com.example.fooddelivery.domain.repository.login

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.fooddelivery.data.login.models.Login

interface LoginRepository {

    suspend fun createAccount(login: Login)
}