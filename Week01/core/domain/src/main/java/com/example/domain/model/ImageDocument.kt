package com.example.domain.model

import java.time.LocalDateTime

data class ImageDocument(
    val collection: String,
    val displaySiteName: String,
    val docUrl: String,
    val height: Int,
    val width: Int,
    val thumbnailUrl: String,
    val imageUrl: String,
    val datetime: LocalDateTime,
)
