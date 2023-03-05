package com.example.fooddelivery.domain.repository.homeScreen

import com.example.fooddelivery.data.homeScreen.models.ResponseTest
import retrofit2.Response

interface HomeRepository {
    suspend fun fetchUser() : Response<ResponseTest>
}