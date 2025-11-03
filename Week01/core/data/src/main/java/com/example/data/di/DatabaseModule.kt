package com.example.data.di

import android.content.Context
import com.example.data.local.database.BookmarkDao
import com.example.data.local.database.BookmarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideBookmarkDatabase(
        @ApplicationContext context: Context,
    ): BookmarkDatabase = BookmarkDatabase.create(context)

    @Provides
    fun provideBookmarkDao(database: BookmarkDatabase): BookmarkDao = database.bookmarkDao()
}
