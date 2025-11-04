package com.example.search.ui

import androidx.compose.runtime.Immutable
import com.example.search.model.DocumentUiModel

@Immutable
data class SearchUiState(
    val query: String = "",
    val items: List<DocumentUiModel> = emptyList(),
)
