package com.example.bookmark.ui

sealed interface BookmarkUiEvent {
    data object AddBookmarkFailure : BookmarkUiEvent

    data object RemoveBookmarkFailure : BookmarkUiEvent
}
