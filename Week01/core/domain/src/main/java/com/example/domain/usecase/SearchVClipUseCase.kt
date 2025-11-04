package com.example.domain.usecase

import com.example.domain.model.VClipDocument
import com.example.domain.model.common.Pageable
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class SearchVClipUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(
        pageable: Pageable<VClipDocument>,
        sort: String = "recency",
        size: Int = 15,
    ): Result<Pageable<VClipDocument>> =
        repository
            .searchVClip(
                query = pageable.query,
                sort = sort,
                page = pageable.page + if (pageable.page == 0) 1 else 0,
                size = size,
            ).mapCatching { newPageable -> pageable.merge(newPageable) }
}
