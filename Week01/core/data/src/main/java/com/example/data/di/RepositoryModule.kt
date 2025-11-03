package com.example.data.di

import com.example.data.repository.BookmarkRepositoryImpl
import com.example.data.repository.KakaoSearchRepository
import com.example.domain.repository.BookmarkRepository
import com.example.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSearchRepository(impl: KakaoSearchRepository): SearchRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(impl: BookmarkRepositoryImpl): BookmarkRepository
}
