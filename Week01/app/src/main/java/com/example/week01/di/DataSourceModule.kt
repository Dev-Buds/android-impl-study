package com.example.week01.di

import com.example.week01.data.source.remote.SearchDataSource
import com.example.week01.data.source.remote.SearchDataSourceImpl
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
    abstract fun bindSearchDataSource(impl: SearchDataSourceImpl): SearchDataSource
}
