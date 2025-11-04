package com.example.bookmark.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookmark.model.BookmarkUiModel
import example.com.designsystem.component.WeekAsyncImage
import example.com.designsystem.theme.LocalWeekSpacing
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekShapes
import example.com.designsystem.theme.WeekTheme
import java.time.LocalDateTime

@Composable
fun BookmarkItem(
    item: BookmarkUiModel,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .aspectRatio(1f)
                .clip(WeekShapes.medium),
    ) {
        WeekAsyncImage(
            url = item.thumbnailUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        BookmarkItemBottomSection(
            url = item.thumbnailUrl,
            isBookmarked = item.isBookmarked,
            onBookmarkClick = onBookmarkClick,
            modifier = modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun BookmarkItemBottomSection(
    url: String,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalWeekSpacing.current
    val background =
        Brush.verticalGradient(
            colors = listOf(Color.Transparent, WeekColors.NeutralOnBackground.copy(alpha = 0.6f)),
        )

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .background(background),
    ) {
        SearchResultItemIcons(
            linkUrl = url,
            isBookmarked = isBookmarked,
            onBookmarkClick = onBookmarkClick,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = spacing.small,
                        end = spacing.small,
                        top = spacing.extraSmall,
                        bottom = spacing.small,
                    ),
        )
    }
}

@Composable
private fun SearchResultItemIcons(
    linkUrl: String?,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalWeekSpacing.current
    val uriHandler = LocalUriHandler.current

    val bookmarkIcon = if (isBookmarked) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        if (!linkUrl.isNullOrBlank()) {
            IconButton(
                onClick = { uriHandler.openUri(linkUrl) },
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                    contentDescription = "웹으로 이동",
                    tint = WeekColors.Primary,
                )
            }

            Spacer(modifier = Modifier.width(spacing.small))
        }

        IconButton(
            onClick = onBookmarkClick,
            modifier = Modifier.size(24.dp),
        ) {
            Icon(
                imageVector = bookmarkIcon,
                contentDescription = "북마크",
                tint = WeekColors.Primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookmarkItemPreview() {
    WeekTheme {
        BookmarkItem(
            item =
                BookmarkUiModel(
                    id = "",
                    thumbnailUrl = "1234",
                    url = "",
                    dateTime = LocalDateTime.now(),
                    bookmarkedAt = LocalDateTime.now(),
                ),
            onBookmarkClick = {},
        )
    }
}
