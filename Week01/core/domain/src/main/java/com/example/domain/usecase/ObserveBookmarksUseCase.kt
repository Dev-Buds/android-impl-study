package com.example.domain.usecase

import com.example.domain.model.BookmarkItem
import com.example.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(): Flow<List<BookmarkItem>> = bookmarkRepository.observeBookmarks()
}
