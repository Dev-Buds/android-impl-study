package com.example.data.remote.model

import com.example.data.util.LocalDateTimeIsoSerializer
import com.example.domain.model.VClipDocument
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SearchVClipDocument(
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("datetime")
    @Serializable(with = LocalDateTimeIsoSerializer::class)
    val datetime: LocalDateTime,
    @SerialName("play_time")
    val playTime: Int,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("author")
    val author: String,
) {
    fun toDomain(): VClipDocument =
        VClipDocument(
            title = title,
            url = url,
            datetime = datetime,
            playTime = playTime,
            thumbnail = thumbnail,
            author = author,
        )
}
