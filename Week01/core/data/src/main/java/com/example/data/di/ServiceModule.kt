package com.example.data.di

import com.example.data.remote.service.KakaoSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideKakaoSearchService(retrofit: Retrofit): KakaoSearchService = retrofit.create(KakaoSearchService::class.java)
}
