package com.example.week01.domain.model

import java.time.LocalDateTime

data class Media(
    val id: Long? = null,
    val title: String,
    val thumbnailUrl: String,
    val createdAt: LocalDateTime,
)
