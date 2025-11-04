package com.example.bookmark.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkTopAppBar(
    onClickSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(text = "내 북마크") },
        modifier = modifier,
        navigationIcon = { BookmarkTopAppBarNavigationIcon(onClick = onClickSearch) },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = WeekColors.NeutralBackground,
            ),
    )
}

@Composable
private fun BookmarkTopAppBarNavigationIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun SearchTopAppBarPreview() {
    WeekTheme {
        BookmarkTopAppBar(
            onClickSearch = {},
        )
    }
}
