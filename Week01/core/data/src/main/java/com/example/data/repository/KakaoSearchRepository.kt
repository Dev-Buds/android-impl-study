package com.example.data.repository

import com.example.data.remote.datasource.SearchRemoteDataSource
import com.example.domain.model.ImageDocument
import com.example.domain.model.VClipDocument
import com.example.domain.model.common.Pageable
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class KakaoSearchRepository @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource,
) : SearchRepository {
    override suspend fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<Pageable<ImageDocument>> =
        remoteDataSource
            .searchImage(
                query = query,
                sort = sort,
                page = page,
                size = size,
            ).mapCatching { response -> response.map(query, page) { item -> item.toDomain() } }

    override suspend fun searchVClip(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<Pageable<VClipDocument>> =
        remoteDataSource
            .searchVClip(
                query = query,
                sort = sort,
                page = page,
                size = size,
            ).mapCatching { response -> response.map(query, page) { item -> item.toDomain() } }
}
