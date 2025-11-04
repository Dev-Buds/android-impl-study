package com.example.bookmark.model

import com.example.domain.model.BookmarkItem
import java.time.LocalDateTime

data class BookmarkUiModel(
    val id: String,
    val url: String,
    val thumbnailUrl: String,
    val dateTime: LocalDateTime,
    val bookmarkedAt: LocalDateTime,
    val isBookmarked: Boolean = true,
) {
    fun toBookmark(): BookmarkItem =
        BookmarkItem(
            id = id,
            url = url,
            thumbnailUrl = thumbnailUrl,
            bookmarkedAt = bookmarkedAt,
            dateTime = dateTime,
        )

    companion object {
        fun from(bookmarkItem: BookmarkItem): BookmarkUiModel =
            BookmarkUiModel(
                id = bookmarkItem.id,
                url = bookmarkItem.url,
                thumbnailUrl = bookmarkItem.thumbnailUrl,
                dateTime = bookmarkItem.dateTime,
                bookmarkedAt = bookmarkItem.bookmarkedAt ?: LocalDateTime.now(),
            )
    }
}
