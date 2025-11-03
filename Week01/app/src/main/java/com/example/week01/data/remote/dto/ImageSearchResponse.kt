package com.example.week01.data.remote.dto

import com.example.week01.domain.model.Media
import kotlinx.serialization.SerialName
import java.time.LocalDateTime

data class ImageSearchResponse(
    @SerialName("meta")
    val meta: SearchMetaResponse,
    @SerialName("documents")
    val documents: List<KakaoImageSearchDocument>,
) {
    data class KakaoImageSearchDocument(
        @SerialName("collection")
        val collection: String,
        @SerialName("thumbnail_url")
        val thumbnailUrl: String,
        @SerialName("image_url")
        val imageUrl: String,
        @SerialName("width")
        val width: Int,
        @SerialName("height")
        val height: Int,
        @SerialName("display_sitename")
        val siteName: String,
        @SerialName("doc_url")
        val docUrl: String,
        @SerialName("datetime")
        val createdAt: LocalDateTime,
    )

    fun toDomain(): List<Media> =
        documents.map { document ->
            Media(
                title = document.siteName,
                thumbnailUrl = document.thumbnailUrl,
                createdAt = document.createdAt,
            )
        }
}
