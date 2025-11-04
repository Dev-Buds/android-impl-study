package com.example.domain.repository

import com.example.domain.model.BookmarkItem
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun observeBookmarks(): Flow<List<BookmarkItem>>

    suspend fun addBookmark(item: BookmarkItem): Result<Unit>

    suspend fun removeBookmark(id: String): Result<Unit>
}
