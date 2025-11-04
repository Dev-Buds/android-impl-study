package com.example.data.local.datasource

import com.example.data.local.database.BookmarkDao
import com.example.data.local.database.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomBookmarkLocalDataSource @Inject constructor(
    private val bookmarkDao: BookmarkDao,
) : BookmarkLocalDataSource {
    override fun observeBookmarks(): Flow<List<BookmarkEntity>> = bookmarkDao.observeBookmarks()

    override suspend fun insertBookmark(bookmark: BookmarkEntity): Result<Unit> =
        runCatching {
            val result = bookmarkDao.insertBookmark(bookmark)
            require(result != -1L) { "북마크 추가 실패" }
        }

    override suspend fun deleteBookmarkById(id: String): Result<Unit> =
        runCatching {
            val result = bookmarkDao.deleteBookmarkById(id)
            require(result > 0) { "북마크 제거 실패 (id=$id)" }
        }
}
