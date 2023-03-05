package com.example.fooddelivery.data.login

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddelivery.data.login.cache.database.LoginDao
import com.example.fooddelivery.data.login.models.Login

@Database(
    version = 1,
    entities = [Login::class]
)
abstract class LoginDatabase: RoomDatabase() {
    abstract fun createAccountDao():LoginDao
}