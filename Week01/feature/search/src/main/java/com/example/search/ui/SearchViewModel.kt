package com.example.search.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ImageDocument
import com.example.domain.model.VClipDocument
import com.example.domain.model.common.Pageable
import com.example.domain.usecase.SearchImageUseCase
import com.example.domain.usecase.SearchVClipUseCase
import com.example.search.model.DocumentUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
    private val searchVClipUseCase: SearchVClipUseCase,
) : ViewModel() {
    private var imagePageable: Pageable<ImageDocument> = Pageable()
    private var vClipPageable: Pageable<VClipDocument> = Pageable()

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _uiEvent: Channel<SearchUiEvent> = Channel()
    val uiEvent: Flow<SearchUiEvent> = _uiEvent.receiveAsFlow()

    fun search(query: String) {
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(items = emptyList())
            imagePageable = Pageable()
            vClipPageable = Pageable()
            return
        }

        viewModelScope.launch {
            val imageResultDeferred = async { searchImage(query) }
            val vClipResultDeferred = async { searchVClip(query) }

            val imageResult =
                imageResultDeferred.await().contents.map(DocumentUiModel.ImageDocumentUiModel::from)
            val vClipResult =
                vClipResultDeferred.await().contents.map(DocumentUiModel.VClipDocumentUiModel::from)

            val sorted = (imageResult + vClipResult).sortedByDescending { item -> item.datetime }
            _uiState.emit(_uiState.value.copy(items = sorted))
        }
    }

    private suspend fun searchImage(query: String): Pageable<ImageDocument> {
        val nextPageable = imagePageable.nextPage(query) ?: return imagePageable

        return searchImageUseCase(nextPageable)
            .onSuccess { newPageable ->
                if (nextPageable.page == 0) _uiEvent.send(SearchUiEvent.ResetListState)
                imagePageable = newPageable
            }.onFailure { exception -> Timber.e(exception.message, exception) }
            .getOrNull() ?: imagePageable
    }

    private suspend fun searchVClip(query: String): Pageable<VClipDocument> {
        val nextPageable = vClipPageable.nextPage(query) ?: return vClipPageable

        return searchVClipUseCase(nextPageable)
            .onSuccess { newPageable ->
                if (nextPageable.page == 0) _uiEvent.send(SearchUiEvent.ResetListState)
                vClipPageable = newPageable
            }.onFailure { exception -> Timber.e(exception.message, exception) }
            .getOrNull() ?: vClipPageable
    }
}

@Immutable
data class SearchUiState(
    val items: List<DocumentUiModel> = emptyList(),
)

sealed interface SearchUiEvent {
    data object ResetListState : SearchUiEvent
}
