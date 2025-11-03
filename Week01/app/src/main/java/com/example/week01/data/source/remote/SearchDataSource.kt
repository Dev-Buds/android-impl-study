package com.example.week01.data.source.remote

import com.example.week01.data.remote.dto.ImageSearchResponse
import com.example.week01.data.remote.dto.VideoSearchResponse

interface SearchDataSource {
    suspend fun fetchImage(
        query: String,
        page: Int,
        size: Int,
    ): ImageSearchResponse

    suspend fun fetchVideo(
        query: String,
        page: Int,
        size: Int,
    ): VideoSearchResponse
}
