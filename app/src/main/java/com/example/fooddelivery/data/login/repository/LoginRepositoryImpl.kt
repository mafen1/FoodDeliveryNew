package com.example.fooddelivery.data.login.repository

import com.example.fooddelivery.data.login.cache.database.LoginDao
import com.example.fooddelivery.data.login.models.Login
import com.example.fooddelivery.domain.repository.login.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginDao: LoginDao) : LoginRepository {
    override suspend fun createAccount(login: Login) = loginDao.createAccount(login)

}