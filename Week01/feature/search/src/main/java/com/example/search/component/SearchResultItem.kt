package com.example.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.search.model.DocumentUiModel
import example.com.designsystem.component.WeekAsyncImage
import example.com.designsystem.theme.LocalWeekSpacing
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekShapes
import example.com.designsystem.theme.WeekTheme
import example.com.designsystem.theme.WeekTypography
import java.net.URI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val ResultDateTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy.MM.dd · HH:mm")

private enum class DocumentType { Image, Video }

private val collectionLabelMap: Map<String, String> =
    mapOf(
        "blog" to "블로그",
        "cafe" to "카페",
        "news" to "뉴스",
    )

private data class SearchResultUiModel(
    val type: DocumentType,
    val thumbnailUrl: String,
    val primaryLabel: String,
    val hostLabel: String,
    val datetime: LocalDateTime,
    val linkUrl: String?,
    val isBookmarked: Boolean,
)

@Composable
fun SearchResultItem(
    item: DocumentUiModel,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiModel =
        when (item) {
            is DocumentUiModel.ImageDocumentUiModel -> item.toUiModel()
            is DocumentUiModel.VClipDocumentUiModel -> item.toUiModel()
        }

    SearchResultBaseItem(
        uiModel = uiModel,
        modifier = modifier,
        onBookmarkClick = onBookmarkClick,
    )
}

@Composable
private fun SearchResultBaseItem(
    uiModel: SearchResultUiModel,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalWeekSpacing.current

    OutlinedCard(
        modifier =
            modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 120.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = WeekColors.NeutralBackground),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(spacing.medium),
        ) {
            WeekAsyncImage(
                url = uiModel.thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .width(120.dp)
                        .fillMaxHeight()
                        .clip(WeekShapes.medium),
            )

            DocumentInfoSection(
                type = uiModel.type,
                primaryLabel = uiModel.primaryLabel,
                hostLabel = uiModel.hostLabel,
                datetime = uiModel.datetime,
                linkUrl = uiModel.linkUrl,
                isBookmarked = uiModel.isBookmarked,
                onBookmarkClick = onBookmarkClick,
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
            )
        }
    }
}

@Composable
private fun DocumentInfoSection(
    type: DocumentType,
    primaryLabel: String,
    hostLabel: String,
    datetime: LocalDateTime,
    linkUrl: String?,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalWeekSpacing.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
            ) {
                TypeChip(type = type)

                if (primaryLabel.isNotBlank()) {
                    Text(
                        text = primaryLabel,
                        style = WeekTypography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            SearchResultItemIcons(
                linkUrl = linkUrl,
                isBookmarked = isBookmarked,
                onBookmarkClick = onBookmarkClick,
            )
        }

        DocumentMetaRow(
            datetime = datetime,
            hostLabel = hostLabel,
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
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
    ) {
        if (!linkUrl.isNullOrBlank()) {
            IconButton(
                onClick = { uriHandler.openUri(linkUrl) },
                modifier = Modifier.size(20.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                    contentDescription = "웹으로 이동",
                    tint = WeekColors.Secondary,
                )
            }
        }

        IconButton(
            onClick = onBookmarkClick,
            modifier = Modifier.size(20.dp),
        ) {
            Icon(
                imageVector = bookmarkIcon,
                contentDescription = "북마크",
                tint = WeekColors.Secondary,
            )
        }
    }
}

@Composable
private fun DocumentMetaRow(
    datetime: LocalDateTime,
    hostLabel: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = datetime.format(ResultDateTimeFormatter),
            style = WeekTypography.labelSmall,
            color = WeekColors.Secondary,
        )

        if (hostLabel.isNotBlank()) {
            Text(
                text = hostLabel,
                style = WeekTypography.labelSmall,
                color = WeekColors.Secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun TypeChip(
    type: DocumentType,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalWeekSpacing.current

    val (label, color) =
        when (type) {
            DocumentType.Image -> "이미지" to WeekColors.Primary
            DocumentType.Video -> "동영상" to WeekColors.Secondary
        }

    Text(
        text = label,
        style = WeekTypography.labelSmall,
        color = color,
        modifier =
            modifier
                .background(
                    color.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(999.dp),
                ).padding(
                    horizontal = spacing.small,
                    vertical = spacing.extraSmall,
                ),
    )
}

private fun DocumentUiModel.ImageDocumentUiModel.toUiModel(): SearchResultUiModel =
    SearchResultUiModel(
        type = DocumentType.Image,
        thumbnailUrl = thumbnailUrl,
        primaryLabel = collection.toCollectionLabel(),
        hostLabel = docUrl.toHost(),
        datetime = datetime,
        linkUrl = docUrl,
        isBookmarked = isBookmarked,
    )

private fun DocumentUiModel.VClipDocumentUiModel.toUiModel(): SearchResultUiModel =
    SearchResultUiModel(
        type = DocumentType.Video,
        thumbnailUrl = thumbnail,
        primaryLabel = author.ifBlank { "알 수 없는 채널" },
        hostLabel = url.toHost(),
        datetime = datetime,
        linkUrl = url,
        isBookmarked = isBookmarked,
    )

private fun String.toCollectionLabel(): String = collectionLabelMap[lowercase()] ?: "기타"

private fun String.toHost(): String = runCatching { URI(this).host.orEmpty() }.getOrDefault("")

@Preview(name = "Image Item Preview")
@Composable
private fun SearchResultImageItemPreview() {
    WeekTheme {
        SearchResultItem(
            onBookmarkClick = {},
            item =
                DocumentUiModel.ImageDocumentUiModel(
                    collection = "blog",
                    docUrl = "https://blog.naver.com/example",
                    thumbnailUrl = "",
                    imageUrl = "",
                    datetime = LocalDateTime.of(2025, 1, 1, 10, 30),
                ),
        )
    }
}

@Preview(name = "Video Item Preview")
@Composable
private fun SearchResultVClipItemPreview() {
    WeekTheme {
        SearchResultItem(
            onBookmarkClick = {},
            item =
                DocumentUiModel.VClipDocumentUiModel(
                    url = "https://www.youtube.com/watch?v=abc123",
                    thumbnail = "",
                    author = "다이스 채널",
                    datetime = LocalDateTime.of(2025, 2, 2, 15, 0),
                ),
        )
    }
}
