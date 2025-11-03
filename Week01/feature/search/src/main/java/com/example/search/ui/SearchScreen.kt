package com.example.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.search.component.SearchResultColumn
import com.example.search.component.SearchTopAppBar
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

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                SearchUiEvent.ResetListState -> listState.scrollToItem(0)
            }
        }
    }

    SearchScreen(
        uiState = uiState,
        listState = listState,
        onQueryChange = viewModel::updateQuery,
        onSearch = viewModel::search,
        onClickStorage = navigateToBookmark,
    )
}

@Composable
private fun SearchScreen(
    uiState: SearchUiState,
    listState: LazyListState,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClickStorage: () -> Unit,
) {
    Scaffold(
        topBar = { SearchTopAppBar(onClickStorage = onClickStorage) },
        containerColor = WeekColors.NeutralBackground,
    ) { innerPadding ->
        SearchScreenContent(
            uiState = uiState,
            listState = listState,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
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
            onQueryChange = {},
            onSearch = {},
            onClickStorage = {},
        )
    }
}
