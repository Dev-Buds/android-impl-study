package com.example.domain.model

import java.time.LocalDateTime

data class VClipDocument(
    val title: String,
    val url: String,
    val datetime: LocalDateTime,
    val playTime: Int,
    val thumbnail: String,
    val author: String,
)
