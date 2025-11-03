package com.example.search.model

import com.example.domain.model.ImageDocument
import com.example.domain.model.VClipDocument
import java.time.LocalDateTime

sealed interface DocumentUiModel {
    val datetime: LocalDateTime

    data class ImageDocumentUiModel(
        val thumbnailUrl: String,
        val imageUrl: String,
        override val datetime: LocalDateTime,
    ) : DocumentUiModel {
        companion object {
            fun from(document: ImageDocument) =
                ImageDocumentUiModel(
                    thumbnailUrl = document.thumbnailUrl,
                    imageUrl = document.imageUrl,
                    datetime = document.datetime,
                )
        }
    }

    data class VClipDocumentUiModel(
        val thumbnail: String,
        val author: String,
        override val datetime: LocalDateTime,
    ) : DocumentUiModel {
        companion object {
            fun from(document: VClipDocument) =
                VClipDocumentUiModel(
                    datetime = document.datetime,
                    thumbnail = document.thumbnail,
                    author = document.author,
                )
        }
    }
}
