package com.example.search.ui

sealed interface SearchUiEvent {
    data object ResetListState : SearchUiEvent
}
