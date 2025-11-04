package com.example.bookmark.ui

import androidx.compose.runtime.Immutable
import com.example.bookmark.model.BookmarkUiModel

@Immutable
data class BookmarkUiState(
    val bookmarkItems: List<BookmarkUiModel> = emptyList(),
)
