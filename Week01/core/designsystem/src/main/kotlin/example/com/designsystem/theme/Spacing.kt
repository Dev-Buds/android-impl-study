package example.com.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WeekSpacing(
    val tiny: Dp = 2.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 24.dp,
)

internal val DefaultWeekSpacing = WeekSpacing()

val LocalWeekSpacing = staticCompositionLocalOf { DefaultWeekSpacing }

val MaterialTheme.spacing: WeekSpacing
    @Composable
    @ReadOnlyComposable
    get() = LocalWeekSpacing.current
