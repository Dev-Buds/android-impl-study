package com.example.week01.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.week01.data.paging.SearchPagingSource
import com.example.week01.data.source.remote.SearchDataSource
import com.example.week01.domain.model.Media
import com.example.week01.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override fun getSearchMedia(query: String): Flow<PagingData<Media>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { SearchPagingSource(searchDataSource, query) },
        ).flow
}
