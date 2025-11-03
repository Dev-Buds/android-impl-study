package com.example.week01.domain.repository

import androidx.paging.PagingData
import com.example.week01.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchMedia(query: String): Flow<PagingData<Media>>
}