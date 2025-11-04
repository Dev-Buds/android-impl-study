package com.example.domain.repository

import com.example.domain.model.ImageDocument
import com.example.domain.model.VClipDocument
import com.example.domain.model.common.Pageable

interface SearchRepository {
    suspend fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<Pageable<ImageDocument>>

    suspend fun searchVClip(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Result<Pageable<VClipDocument>>
}
