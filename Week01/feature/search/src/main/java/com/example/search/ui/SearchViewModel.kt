package com.example.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ImageDocument
import com.example.domain.model.VClipDocument
import com.example.domain.model.common.Pageable
import com.example.domain.usecase.AddBookmarkUseCase
import com.example.domain.usecase.ObserveBookmarksUseCase
import com.example.domain.usecase.RemoveBookmarkUseCase
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
    private val searchVClipUseCase: SearchVClipUseCase,
    private val observeBookmarksUseCase: ObserveBookmarksUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
) : ViewModel() {
    private var imagePageable: Pageable<ImageDocument> = Pageable()
    private var vClipPageable: Pageable<VClipDocument> = Pageable()
    private var bookmarkedIdSet: Set<String> = emptySet()

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _uiEvent: Channel<SearchUiEvent> = Channel()
    val uiEvent: Flow<SearchUiEvent> = _uiEvent.receiveAsFlow()

    init {
        observeBookmarkChanged()
    }

    fun updateQuery(newQuery: String) {
        if (_uiState.value.query == newQuery) return
        _uiState.value = _uiState.value.copy(query = newQuery)
    }

    fun search() {
        val query = _uiState.value.query
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
            _uiState.emit(_uiState.value.copy(items = applyBookmarkState(sorted)))
        }
    }

    fun toggleBookmark(item: DocumentUiModel) {
        if (item.isBookmarked) removeBookmark(item.id) else addBookmark(item)
    }

    private fun observeBookmarkChanged() {
        observeBookmarksUseCase()
            .onEach { bookmarkItems ->
                bookmarkedIdSet = bookmarkItems.map { item -> item.id }.toSet()
                val updated = applyBookmarkState(_uiState.value.items)
                _uiState.value = _uiState.value.copy(items = updated)
            }.launchIn(viewModelScope)
    }

    private fun applyBookmarkState(items: List<DocumentUiModel>): List<DocumentUiModel> =
        items.map { item ->
            val isBookmarked = bookmarkedIdSet.contains(item.id)
            item.updateBookmarkState(isBookmarked = isBookmarked)
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

    private fun addBookmark(item: DocumentUiModel) {
        viewModelScope.launch {
            addBookmarkUseCase(item.toBookmark())
                .onFailure { exception -> Timber.e(exception.message, exception) }
        }
    }

    private fun removeBookmark(id: String) {
        viewModelScope.launch {
            removeBookmarkUseCase(id)
                .onFailure { exception -> Timber.e(exception.message, exception) }
        }
    }
}
