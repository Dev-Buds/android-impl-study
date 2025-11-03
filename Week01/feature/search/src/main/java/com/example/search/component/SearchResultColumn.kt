package com.example.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.search.model.DocumentUiModel
import example.com.designsystem.theme.LocalWeekSpacing
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekTheme
import example.com.designsystem.util.rememberScrolledToEnd
import example.com.designsystem.util.verticalFadeEdge
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import java.time.LocalDateTime

@Composable
fun SearchResultColumn(
    items: List<DocumentUiModel>,
    state: LazyListState,
    onScrolledToEnd: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val localSpacing = LocalWeekSpacing.current
    val isScrolledToEnd by state.rememberScrolledToEnd(threshold = 5)

    LaunchedEffect(Unit) {
        snapshotFlow { isScrolledToEnd }
            .distinctUntilChanged()
            .filter { it }
            .collect { onScrolledToEnd() }
    }

    LazyColumn(
        state = state,
        modifier = modifier.verticalFadeEdge(color = WeekColors.NeutralBackground),
        verticalArrangement = Arrangement.spacedBy(localSpacing.medium),
    ) {
        item { Spacer(modifier = Modifier.height(localSpacing.small)) }
        items(items = items, key = { it.hashCode() }) { item -> SearchResultItem(item = item) }
        item { Spacer(modifier = Modifier.height(localSpacing.small)) }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchResultColumnPreview() {
    WeekTheme {
        SearchResultColumn(
            onScrolledToEnd = {},
            state = rememberLazyListState(),
            items =
                List(5) {
                    DocumentUiModel.ImageDocumentUiModel(
                        collection = "",
                        docUrl = "",
                        thumbnailUrl = "",
                        imageUrl = "",
                        datetime = LocalDateTime.now(),
                    )
                },
        )
    }
}
