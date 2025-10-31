package com.example.data.remote.model

import com.example.data.util.LocalDateTimeIsoSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SearchImageDocument(
    @SerialName("collection")
    val collection: String,
    @SerialName("display_sitename")
    val displaySiteName: String,
    @SerialName("doc_url")
    val docUrl: String,
    @SerialName("height")
    val height: Int,
    @SerialName("width")
    val width: Int,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("datetime")
    @Serializable(with = LocalDateTimeIsoSerializer::class)
    val datetime: LocalDateTime,
)
