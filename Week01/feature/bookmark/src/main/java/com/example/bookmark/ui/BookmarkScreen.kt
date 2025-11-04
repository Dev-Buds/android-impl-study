package com.example.bookmark.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.bookmark.component.BookmarkEmptyView
import com.example.bookmark.component.BookmarkItem
import com.example.bookmark.component.BookmarkTopAppBar
import com.example.bookmark.model.BookmarkUiModel
import example.com.designsystem.theme.LocalWeekSpacing
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekTheme

@Composable
fun BookmarkScreen(
    navigateToSearchScreen: () -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                BookmarkUiEvent.AddBookmarkFailure ->
                    snackbarState.showSnackbar("북마크 추가에 실패했습니다")

                BookmarkUiEvent.RemoveBookmarkFailure ->
                    snackbarState.showSnackbar("북마크 삭제에 실패했습니다")
            }
        }
    }

    BookmarkScreen(
        uiState = uiState,
        snackbarState = snackbarState,
        onClickSearch = navigateToSearchScreen,
        onClickBookmark = viewModel::toggleBookmark,
    )
}

@Composable
private fun BookmarkScreen(
    uiState: BookmarkUiState,
    snackbarState: SnackbarHostState,
    onClickSearch: () -> Unit,
    onClickBookmark: (item: BookmarkUiModel) -> Unit,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
        topBar = { BookmarkTopAppBar(onClickSearch = onClickSearch) },
        containerColor = WeekColors.NeutralBackground,
    ) { innerPadding ->
        if (uiState.bookmarkItems.isEmpty()) {
            BookmarkEmptyView(
                modifier = Modifier.padding(innerPadding),
                onClickSearch = onClickSearch,
            )
            return@Scaffold
        }

        BookmarkScreenContent(
            uiState = uiState,
            onClickBookmark = onClickBookmark,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun BookmarkScreenContent(
    uiState: BookmarkUiState,
    onClickBookmark: (item: BookmarkUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalWeekSpacing.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(spacing.small),
        verticalArrangement = Arrangement.spacedBy(spacing.small),
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
    ) {
        items(uiState.bookmarkItems, key = { it.id }) { item ->
            BookmarkItem(
                item = item,
                onBookmarkClick = { onClickBookmark(item) },
            )
        }
    }
}

@Preview
@Composable
private fun BookmarkScreenPreview() {
    WeekTheme {
        BookmarkScreen(
            uiState = BookmarkUiState(),
            snackbarState = remember { SnackbarHostState() },
            onClickBookmark = {},
            onClickSearch = {},
        )
    }
}
