package com.example.data.di

import com.example.data.remote.datasource.KakaoSearchRemoteDataSource
import com.example.data.remote.datasource.SearchRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindSearchDataSource(impl: KakaoSearchRemoteDataSource): SearchRemoteDataSource
}
