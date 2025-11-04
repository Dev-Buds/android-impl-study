package com.example.bookmark.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookmark.model.BookmarkUiModel
import com.example.domain.usecase.AddBookmarkUseCase
import com.example.domain.usecase.ObserveBookmarksUseCase
import com.example.domain.usecase.RemoveBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val observeBookmarkUseCase: ObserveBookmarksUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
) : ViewModel() {
    private var bookmarkCapture: List<BookmarkUiModel> = emptyList()

    private val _uiState: MutableStateFlow<BookmarkUiState> = MutableStateFlow(BookmarkUiState())
    val uiState: StateFlow<BookmarkUiState> = _uiState.asStateFlow()

    private val _uiEvent: Channel<BookmarkUiEvent> = Channel()
    val uiEvent: Flow<BookmarkUiEvent> = _uiEvent.receiveAsFlow()

    init {
        observeBookmarks()
    }

    fun toggleBookmark(item: BookmarkUiModel) {
        if (item.isBookmarked) removeBookmark(item.id) else addBookmark(item)
    }

    private fun addBookmark(item: BookmarkUiModel) {
        viewModelScope.launch {
            addBookmarkUseCase(item.toBookmark())
                .onFailure { exception ->
                    Timber.e(exception.message, exception)
                    _uiEvent.send(BookmarkUiEvent.AddBookmarkFailure)
                }
        }
    }

    private fun removeBookmark(id: String) {
        viewModelScope.launch {
            removeBookmarkUseCase(id)
                .onFailure { exception ->
                    Timber.e(exception.message, exception)
                    _uiEvent.send(BookmarkUiEvent.RemoveBookmarkFailure)
                }
        }
    }

    private fun observeBookmarks() {
        observeBookmarkUseCase()
            .onEach { bookmarkItems ->
                val currentBookmarks = bookmarkItems.map(BookmarkUiModel::from)
                val previousCapture = bookmarkCapture
                val captureIds = previousCapture.map { it.id }.toSet()
                val currentBookmarksById = currentBookmarks.associateBy(BookmarkUiModel::id)

                val updatedExisting =
                    previousCapture.map { captured ->
                        currentBookmarksById[captured.id]?.copy(isBookmarked = true)
                            ?: captured.copy(isBookmarked = false)
                    }
                val newBookmarks = currentBookmarks.filterNot { captureIds.contains(it.id) }

                bookmarkCapture = updatedExisting + newBookmarks
                _uiState.update { uiState.value.copy(bookmarkItems = bookmarkCapture) }
            }.launchIn(viewModelScope)
    }
}
