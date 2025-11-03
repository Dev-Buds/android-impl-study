package com.example.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.BookmarkItem
import java.time.LocalDateTime

@Entity
data class BookmarkEntity(
    @PrimaryKey val id: String,
    val url: String,
    val thumbnailUrl: String,
    val dateTime: LocalDateTime,
    val bookmarkedAt: LocalDateTime? = LocalDateTime.now(),
)

fun BookmarkEntity.toDomain(): BookmarkItem =
    BookmarkItem(
        id = id,
        url = url,
        thumbnailUrl = thumbnailUrl,
        dateTime = dateTime,
        bookmarkedAt = bookmarkedAt,
    )

fun BookmarkItem.toEntity(): BookmarkEntity =
    BookmarkEntity(
        id = id,
        url = url,
        thumbnailUrl = thumbnailUrl,
        dateTime = dateTime,
        bookmarkedAt = bookmarkedAt,
    )
