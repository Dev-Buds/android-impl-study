package com.example.data.remote.datasource

import com.example.data.remote.model.SearchImageDocument
import com.example.data.remote.model.SearchResponse
import com.example.data.remote.model.SearchVClipDocument
import com.example.data.remote.service.KakaoSearchService
import com.example.data.util.safeApiCall
import javax.inject.Inject

class KakaoSearchRemoteDataSource @Inject constructor(
    private val service: KakaoSearchService,
) : SearchRemoteDataSource {
    override suspend fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<SearchResponse<SearchImageDocument>> = safeApiCall { service.searchImage(query, sort, page, size) }

    override suspend fun searchVClip(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<SearchResponse<SearchVClipDocument>> = safeApiCall { service.searchVClip(query, sort, page, size) }
}
