package com.example.week01.data.remote.client

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RetrofitClient(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: String,
) {
    private val contentType = "application/json".toMediaType()
    private val json =
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }

    val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }
}
