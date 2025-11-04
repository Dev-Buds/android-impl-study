package com.example.bookmark.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import example.com.designsystem.theme.LocalWeekSpacing
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekTheme
import example.com.designsystem.theme.WeekTypography

@Composable
fun BookmarkEmptyView(
    modifier: Modifier = Modifier,
    onClickSearch: () -> Unit,
) {
    val spacing = LocalWeekSpacing.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.small),
        ) {
            Icon(
                imageVector = Icons.Rounded.BookmarkBorder,
                contentDescription = null,
                tint = WeekColors.Secondary,
                modifier = Modifier.size(40.dp),
            )

            Text(
                text = "저장한 이미지가 없습니다",
                style = WeekTypography.bodyLarge,
                color = WeekColors.NeutralOnBackground,
            )

            Text(
                text = "마음에 드는 이미지를 검색해서 북마크해 보세요",
                style = WeekTypography.bodyMedium,
                color = WeekColors.NeutralOnBackground.copy(alpha = 0.7f),
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            TextButton(onClick = onClickSearch) {
                Text(
                    text = "검색하러 가기",
                    style = WeekTypography.labelLarge,
                    color = WeekColors.Secondary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookmarkEmptyViewPreview() {
    WeekTheme {
        BookmarkEmptyView(
            onClickSearch = {},
        )
    }
}
