package com.example.abbystowerapp.Data.Remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // Emulador: 10.0.2.2 es el localhost de tu PC.
    // Celular físico: usa la IP de tu PC, ej. "http://192.168.1.50:5070/"
    const val BASE_URL = "http://10.0.2.2:5070/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    val api: AbbysApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AbbysApi::class.java)
}