package com.example.week01.data.remote.dto

import com.example.week01.domain.model.Media
import kotlinx.serialization.SerialName
import java.time.LocalDateTime

data class VideoSearchResponse(
    @SerialName("meta")
    val meta: SearchMetaResponse,
    @SerialName("documents")
    val documents: List<KakaoVideoSearchDocument>,
) {
    data class KakaoVideoSearchDocument(
        @SerialName("title")
        val title: String,
        @SerialName("url")
        val url: String,
        @SerialName("datetime")
        val createdAt: LocalDateTime,
        @SerialName("play_time")
        val playTime: Int,
        @SerialName("thumbnail")
        val thumbnailUrl: String,
        @SerialName("author")
        val author: String,
    )

    fun toDomain(): List<Media> =
        documents.map { document ->
            Media(
                title = document.title,
                thumbnailUrl = document.thumbnailUrl,
                createdAt = document.createdAt,
            )
        }
}
