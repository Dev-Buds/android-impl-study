package com.example.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.search.component.SearchResultColumn
import com.example.search.component.SearchTopAppBar
import com.example.search.model.DocumentUiModel
import example.com.designsystem.component.WeekSearchBar
import example.com.designsystem.theme.LocalWeekSpacing
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekTheme

@Composable
fun SearchScreen(
    navigateToBookmark: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState: SearchUiState by viewModel.uiState.collectAsState()
    val listState: LazyListState = rememberLazyListState()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                SearchUiEvent.ResetListState -> listState.scrollToItem(0)
                SearchUiEvent.AddBookmarkFailure ->
                    snackbarState.showSnackbar("북마크 추가에 실패했습니다")
                SearchUiEvent.RemoveBookmarkFailure ->
                    snackbarState.showSnackbar("북마크 삭제에 실패했습니다")
            }
        }
    }

    SearchScreen(
        uiState = uiState,
        listState = listState,
        snackbarState = snackbarState,
        onQueryChange = viewModel::updateQuery,
        onSearch = viewModel::search,
        onClickBookmark = viewModel::toggleBookmark,
        onClickStorage = navigateToBookmark,
    )
}

@Composable
private fun SearchScreen(
    uiState: SearchUiState,
    listState: LazyListState,
    snackbarState: SnackbarHostState,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClickBookmark: (item: DocumentUiModel) -> Unit,
    onClickStorage: () -> Unit,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
        topBar = { SearchTopAppBar(onClickStorage = onClickStorage) },
        containerColor = WeekColors.NeutralBackground,
    ) { innerPadding ->
        SearchScreenContent(
            uiState = uiState,
            listState = listState,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            onBookmarkClick = onClickBookmark,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun SearchScreenContent(
    uiState: SearchUiState,
    listState: LazyListState,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onBookmarkClick: (item: DocumentUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val localSpacing = LocalWeekSpacing.current

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = localSpacing.large),
    ) {
        WeekSearchBar(
            query = uiState.query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
        )

        Spacer(modifier = Modifier.height(localSpacing.medium))

        SearchResultColumn(
            items = uiState.items,
            state = listState,
            onScrolledToEnd = onSearch,
            onBookmarkClick = onBookmarkClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    WeekTheme {
        SearchScreen(
            uiState = SearchUiState(),
            listState = rememberLazyListState(),
            snackbarState = remember { SnackbarHostState() },
            onQueryChange = {},
            onSearch = {},
            onClickBookmark = {},
            onClickStorage = {},
        )
    }
}
