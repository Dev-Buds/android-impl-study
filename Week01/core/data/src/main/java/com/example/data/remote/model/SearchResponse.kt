package com.example.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse<T>(
    @SerialName("meta")
    val meta: Meta,
    @SerialName("documents")
    val documents: List<T>,
) {
    @Serializable
    data class Meta(
        @SerialName("is_end")
        val isEnd: Boolean,
        @SerialName("pageable_count")
        val pageableCount: Int,
        @SerialName("total_count")
        val totalCount: Int,
    )
}
