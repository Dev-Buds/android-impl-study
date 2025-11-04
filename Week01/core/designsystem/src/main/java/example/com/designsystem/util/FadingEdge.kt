package example.com.designsystem.util

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import example.com.designsystem.theme.WeekTheme
import example.com.designsystem.theme.spacing

enum class FadeSide { LEFT, RIGHT, BOTTOM, TOP }

fun Size.getFadeOffsets(side: FadeSide): Pair<Offset, Offset> =
    when (side) {
        FadeSide.LEFT -> Offset.Zero to Offset(width, 0f)
        FadeSide.RIGHT -> Offset(width, 0f) to Offset.Zero
        FadeSide.BOTTOM -> Offset(0f, height) to Offset.Zero
        FadeSide.TOP -> Offset.Zero to Offset(0f, height)
    }

fun Modifier.fadingEdge(
    vararg sides: FadeSide,
    color: Color,
    width: Dp,
    isVisible: Boolean,
    spec: AnimationSpec<Dp>? = null,
) = composed {
    require(width > 0.dp) { "Invalid fade width: Width must be greater than 0" }
    require(sides.isNotEmpty()) { "Invalid fade sides: Must provide at least one side" }

    val animatedWidth =
        spec?.let {
            animateDpAsState(
                targetValue = if (isVisible) width else 0.dp,
                animationSpec = spec,
                label = "Fade width",
            ).value
        }

    drawWithContent {
        // Draw the content
        this@drawWithContent.drawContent()

        // Go through all provided sides
        sides.forEach { side ->
            // Get start and end gradient offsets
            val (start, end) = this.size.getFadeOffsets(side)

            // Define the static width
            val staticWidth = if (isVisible) width.toPx() else 0f
            // Define the final width
            val widthPx = animatedWidth?.toPx() ?: staticWidth

            // Calculate fraction based on view size
            val fraction =
                when (side) {
                    FadeSide.LEFT, FadeSide.RIGHT -> widthPx / this.size.width
                    FadeSide.BOTTOM, FadeSide.TOP -> widthPx / this.size.height
                }

            // Draw the gradient
            drawRect(
                brush =
                    Brush.linearGradient(
                        0f to color,
                        fraction to Color.Transparent,
                        start = start,
                        end = end,
                    ),
                size = this.size,
            )
        }
    }
}

fun Modifier.horizontalFadeEdge(
    color: Color,
    isVisible: Boolean = true,
    width: Dp = 8.dp,
    spec: AnimationSpec<Dp>? = null,
) = fadingEdge(
    FadeSide.LEFT,
    FadeSide.RIGHT,
    color = color,
    width = width,
    isVisible = isVisible,
    spec = spec,
)

fun Modifier.verticalFadeEdge(
    color: Color,
    isVisible: Boolean = true,
    width: Dp = 8.dp,
    spec: AnimationSpec<Dp>? = null,
) = fadingEdge(
    FadeSide.TOP,
    FadeSide.BOTTOM,
    color = color,
    width = width,
    isVisible = isVisible,
    spec = spec,
)

private val fadingEdgeSampleItems = (1..12).map { "Item $it" }

@Composable
private fun FadingEdgePreviewChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 2.dp,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            modifier =
                Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small,
                ),
        )
    }
}

@Preview(name = "FadingEdge – Horizontal", showBackground = true)
@Composable
private fun HorizontalFadingEdgePreview() {
    WeekTheme {
        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.large),
        ) {
            LazyRow(
                modifier =
                    Modifier
                        .height(96.dp)
                        .horizontalFadeEdge(
                            color = MaterialTheme.colorScheme.surface,
                            width = 32.dp,
                        ).padding(vertical = MaterialTheme.spacing.medium),
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.extraLarge),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            ) {
                items(fadingEdgeSampleItems) { item ->
                    FadingEdgePreviewChip(text = item)
                }
            }
        }
    }
}

@Preview(name = "FadingEdge – Vertical", showBackground = true)
@Composable
private fun VerticalFadingEdgePreview() {
    WeekTheme {
        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.large),
        ) {
            LazyColumn(
                modifier =
                    Modifier
                        .height(200.dp)
                        .verticalFadeEdge(
                            color = MaterialTheme.colorScheme.surface,
                            width = 32.dp,
                        ).padding(horizontal = MaterialTheme.spacing.extraLarge),
                contentPadding = PaddingValues(vertical = MaterialTheme.spacing.extraLarge),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            ) {
                items(fadingEdgeSampleItems) { item ->
                    FadingEdgePreviewChip(text = item)
                }
            }
        }
    }
}
