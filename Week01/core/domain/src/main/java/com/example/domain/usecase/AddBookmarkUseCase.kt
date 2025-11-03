package com.example.domain.usecase

import com.example.domain.model.BookmarkItem
import com.example.domain.repository.BookmarkRepository
import javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    suspend operator fun invoke(item: BookmarkItem): Result<Unit> = bookmarkRepository.addBookmark(item)
}
