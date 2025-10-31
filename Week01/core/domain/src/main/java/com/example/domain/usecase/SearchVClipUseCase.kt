package com.example.domain.usecase

import com.example.domain.model.VClipDocument
import com.example.domain.model.common.Pageable
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class SearchVClipUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(
        query: String,
        page: Int,
        sort: String = "recency",
        size: Int = 15,
    ): Result<Pageable<VClipDocument>> = repository.searchVClip(query, sort, page, size)
}
