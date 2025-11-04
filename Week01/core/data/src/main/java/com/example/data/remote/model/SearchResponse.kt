package com.example.data.remote.model

import com.example.domain.model.common.Pageable
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

    fun <R> map(
        query: String,
        currentPage: Int,
        mapper: (T) -> R,
    ): Pageable<R> =
        Pageable(
            query = query,
            isEnd = meta.isEnd,
            pageableCount = meta.pageableCount,
            totalCount = meta.totalCount,
            contents = documents.map(mapper),
            page = currentPage,
        )
}
