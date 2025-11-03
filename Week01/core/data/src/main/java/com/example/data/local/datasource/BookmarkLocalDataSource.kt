package com.example.data.local.datasource

import com.example.data.local.database.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkLocalDataSource {
    fun observeBookmarks(): Flow<List<BookmarkEntity>>

    suspend fun insertBookmark(bookmark: BookmarkEntity): Result<Unit>

    suspend fun deleteBookmarkById(id: String): Result<Unit>
}
