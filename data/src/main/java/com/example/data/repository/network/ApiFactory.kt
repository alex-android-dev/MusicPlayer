package com.example.data.repository.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.deezer.com/"

internal object ApiFactory {

    private val client = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private val retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()


    val apiService: ApiService = retrofit.create()
}