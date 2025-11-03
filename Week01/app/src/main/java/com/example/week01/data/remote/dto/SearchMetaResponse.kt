package com.example.week01.data.remote.dto

import kotlinx.serialization.SerialName

data class SearchMetaResponse(
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("pageable_count")
    val pageableCount: Int,
    @SerialName("is_end")
    val isEnd: Boolean,
)
