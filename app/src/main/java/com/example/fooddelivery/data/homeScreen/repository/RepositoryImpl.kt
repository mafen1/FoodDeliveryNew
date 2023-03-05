package com.example.fooddelivery.data.homeScreen.repository

import com.example.fooddelivery.data.homeScreen.api.ApiService
import com.example.fooddelivery.data.homeScreen.models.ResponseTest
import com.example.fooddelivery.domain.repository.homeScreen.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiService: ApiService) : HomeRepository {

    override suspend fun fetchUser(): Response<ResponseTest> =
        apiService.fetchUser()

}