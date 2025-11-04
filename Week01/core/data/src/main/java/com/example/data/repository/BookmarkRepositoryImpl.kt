package com.example.data.repository

import com.example.data.local.database.toDomain
import com.example.data.local.database.toEntity
import com.example.data.local.datasource.BookmarkLocalDataSource
import com.example.domain.model.BookmarkItem
import com.example.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val localDataSource: BookmarkLocalDataSource,
) : BookmarkRepository {
    override fun observeBookmarks(): Flow<List<BookmarkItem>> =
        localDataSource
            .observeBookmarks()
            .map { entities -> entities.map { entity -> entity.toDomain() } }

    override suspend fun addBookmark(item: BookmarkItem): Result<Unit> = localDataSource.insertBookmark(item.toEntity())

    override suspend fun removeBookmark(id: String): Result<Unit> = localDataSource.deleteBookmarkById(id)
}
