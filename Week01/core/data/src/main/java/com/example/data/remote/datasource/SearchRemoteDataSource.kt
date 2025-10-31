package com.example.data.remote.datasource

import com.example.data.remote.model.SearchImageDocument
import com.example.data.remote.model.SearchResponse
import com.example.data.remote.model.SearchVClipDocument

interface SearchRemoteDataSource {
    suspend fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<SearchResponse<SearchImageDocument>>

    suspend fun searchVClip(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<SearchResponse<SearchVClipDocument>>
}
