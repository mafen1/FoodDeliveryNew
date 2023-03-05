package com.example.fooddelivery.data.homeScreen.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
    private val baseUrl = "http://192.168.1.99:8080"
    private var retrofit: Retrofit? = null

    private fun getClient(baseUrl: String): Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun apiService() = getClient(baseUrl).create(ApiService::class.java)  ?: throw IllegalAccessException("problems in ApiClient")
}