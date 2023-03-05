package com.example.fooddelivery.data.homeScreen.api

import com.example.fooddelivery.data.homeScreen.models.ResponseTest
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

interface ApiService {
    @GET("/user")
    suspend fun fetchUser(

    ): Response<ResponseTest>

    class ApiServiceImpl @Inject constructor(): ApiService{
        override suspend fun fetchUser(): Response<ResponseTest> {
            return ApiClient.apiService().fetchUser()
        }

    }
}