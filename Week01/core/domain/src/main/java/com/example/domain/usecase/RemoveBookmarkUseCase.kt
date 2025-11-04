package com.example.domain.usecase

import com.example.domain.repository.BookmarkRepository
import javax.inject.Inject

class RemoveBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    suspend operator fun invoke(id: String): Result<Unit> = bookmarkRepository.removeBookmark(id)
}
