package com.example.search.ui

sealed interface SearchUiEvent {
    data object ResetListState : SearchUiEvent

    data object AddBookmarkFailure : SearchUiEvent

    data object RemoveBookmarkFailure : SearchUiEvent
}
