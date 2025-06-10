package com.example.cryptofinder.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}