package com.example.search.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Storage
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
fun SearchTopAppBar(
    onClickStorage: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(text = "검색화면") },
        modifier = modifier,
        actions = { SearchTopAppBarAction(onClick = onClickStorage) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = WeekColors.NeutralBackground
        ),
    )
}

@Composable
private fun SearchTopAppBarAction(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Rounded.Storage,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun SearchTopAppBarPreview() {
    WeekTheme {
        SearchTopAppBar(
            onClickStorage = {},
        )
    }
}
