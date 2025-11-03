package com.example.search.model

import com.example.domain.model.ImageDocument
import com.example.domain.model.VClipDocument
import java.time.LocalDateTime

sealed interface DocumentUiModel {
    val datetime: LocalDateTime
    val isBookmarked: Boolean

    data class ImageDocumentUiModel(
        override val datetime: LocalDateTime,
        override val isBookmarked: Boolean = false,
        val collection: String,
        val docUrl: String,
        val thumbnailUrl: String,
        val imageUrl: String,
    ) : DocumentUiModel {
        companion object {
            fun from(document: ImageDocument) =
                ImageDocumentUiModel(
                    collection = document.collection,
                    docUrl = document.docUrl,
                    thumbnailUrl = document.thumbnailUrl,
                    imageUrl = document.imageUrl,
                    datetime = document.datetime,
                )
        }
    }

    data class VClipDocumentUiModel(
        override val datetime: LocalDateTime,
        override val isBookmarked: Boolean = false,
        val url: String,
        val thumbnail: String,
        val author: String,
    ) : DocumentUiModel {
        companion object {
            fun from(document: VClipDocument) =
                VClipDocumentUiModel(
                    url = document.url,
                    thumbnail = document.thumbnail,
                    author = document.author,
                    datetime = document.datetime,
                )
        }
    }
}
