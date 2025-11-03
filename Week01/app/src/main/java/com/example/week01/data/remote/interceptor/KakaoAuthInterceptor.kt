package com.example.week01.data.remote.interceptor

import com.example.week01.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class KakaoAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest =
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "KakaoAK ${BuildConfig.KAKAO_API_KEY}")
                .build()
        return chain.proceed(newRequest)
    }
}
