package com.example.domain.model

import java.time.LocalDateTime

data class BookmarkItem(
    val id: String,
    val url: String,
    val thumbnailUrl: String,
    val dateTime: LocalDateTime,
    val bookmarkedAt: LocalDateTime? = null,
)
