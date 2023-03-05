package com.example.fooddelivery.data.login.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.fooddelivery.data.login.models.Login

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createAccount(login: Login)
}