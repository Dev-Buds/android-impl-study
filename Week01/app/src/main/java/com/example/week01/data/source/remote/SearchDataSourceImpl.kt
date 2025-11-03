package com.example.week01.data.source.remote

import com.example.week01.data.remote.dto.ImageSearchResponse
import com.example.week01.data.remote.dto.VideoSearchResponse
import com.example.week01.data.remote.service.SearchService
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchDataSource {
    override suspend fun fetchImage(
        query: String,
        page: Int,
        size: Int,
    ): ImageSearchResponse =
        searchService.searchImage(
            query = query,
            sort = null,
            page = page.toString(),
            size = size.toString(),
        )

    override suspend fun fetchVideo(
        query: String,
        page: Int,
        size: Int,
    ): VideoSearchResponse =
        searchService.searchVideo(
            query = query,
            sort = null,
            page = page.toString(),
            size = size.toString(),
        )
}
