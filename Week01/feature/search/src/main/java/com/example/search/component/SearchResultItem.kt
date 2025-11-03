package com.example.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.search.model.DocumentUiModel
import example.com.designsystem.component.WeekAsyncImage
import example.com.designsystem.theme.LocalWeekSpacing
import example.com.designsystem.theme.WeekColors
import example.com.designsystem.theme.WeekShapes
import example.com.designsystem.theme.WeekTheme
import example.com.designsystem.theme.WeekTypography
import java.time.LocalDateTime

@Composable
fun SearchResultItem(
    item: DocumentUiModel,
    modifier: Modifier = Modifier,
) {
    when (item) {
        is DocumentUiModel.ImageDocumentUiModel ->
            ImageDocumentItem(item = item, modifier = modifier)

        is DocumentUiModel.VClipDocumentUiModel ->
            VClipDocumentItem(item = item, modifier = modifier)
    }
}

@Composable
private fun ImageDocumentItem(
    item: DocumentUiModel.ImageDocumentUiModel,
    modifier: Modifier = Modifier,
) {
    val localSpacing = LocalWeekSpacing.current

    OutlinedCard(
        modifier =
            modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
        colors =
            CardDefaults.outlinedCardColors(
                containerColor = WeekColors.NeutralBackground,
            ),
        elevation =
            CardDefaults.outlinedCardElevation(
                defaultElevation = 2.dp,
            ),
    ) {
        Column {
            Text(
                text = "이미지",
                color = WeekColors.NeutralBackground,
                style = WeekTypography.labelSmall,
                modifier =
                    Modifier
                        .padding(localSpacing.medium)
                        .background(
                            color = WeekColors.Primary,
                            shape = WeekShapes.small,
                        ).padding(localSpacing.extraSmall),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(localSpacing.small),
                modifier = Modifier.padding(localSpacing.medium),
            ) {
                WeekAsyncImage(
                    url = item.thumbnailUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.weight(1f),
                )

                Text(
                    text = item.datetime.toString(),
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun VClipDocumentItem(
    item: DocumentUiModel.VClipDocumentUiModel,
    modifier: Modifier = Modifier,
) {
    val localSpacing = LocalWeekSpacing.current

    OutlinedCard(
        modifier =
            modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
        colors =
            CardDefaults.outlinedCardColors(
                containerColor = WeekColors.NeutralBackground,
            ),
        elevation =
            CardDefaults.outlinedCardElevation(
                defaultElevation = 2.dp,
            ),
    ) {
        Column {
            Text(
                text = "동영상",
                color = WeekColors.NeutralBackground,
                style = WeekTypography.labelSmall,
                modifier =
                    Modifier
                        .padding(localSpacing.medium)
                        .background(
                            color = WeekColors.Primary,
                            shape = WeekShapes.small,
                        ).padding(localSpacing.extraSmall),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(localSpacing.small),
                modifier = Modifier.padding(localSpacing.medium),
            ) {
                WeekAsyncImage(
                    url = item.thumbnail,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.weight(1f),
                )

                Text(
                    text = item.datetime.toString(),
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchResultItemPreview() {
    WeekTheme {
        SearchResultItem(
            item =
                DocumentUiModel.ImageDocumentUiModel(
                    thumbnailUrl = "",
                    imageUrl = "",
                    datetime = LocalDateTime.of(2025, 1, 1, 1, 1),
                ),
        )
    }
}
