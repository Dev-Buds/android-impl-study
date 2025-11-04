package example.com.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Primary40 = Color(0xFF1B5FFF)
private val Primary80 = Color(0xFFB2C7FF)
private val Primary90 = Color(0xFFD9E2FF)

private val Secondary40 = Color(0xFF6A4CE0)
private val Secondary80 = Color(0xFFCABEFF)
private val Secondary90 = Color(0xFFE4DEFF)

private val Tertiary40 = Color(0xFFFFB01F)
private val Tertiary80 = Color(0xFFFFD8A3)
private val Tertiary90 = Color(0xFFFFEDD2)

private val Neutral10 = Color(0xFF111318)
private val Neutral20 = Color(0xFF1E2126)
private val Neutral90 = Color(0xFFE2E4E8)
private val Neutral95 = Color(0xFFF0F2F5)
private val Neutral99 = Color(0xFFFAFBFF)

private val NeutralVariant30 = Color(0xFF3C424C)
private val NeutralVariant50 = Color(0xFF6C727D)
private val NeutralVariant80 = Color(0xFFC4C8CF)
private val NeutralVariant90 = Color(0xFFE0E3E8)

internal val WeekLightColorScheme =
    lightColorScheme(
        primary = Primary40,
        onPrimary = Color.White,
        primaryContainer = Primary90,
        onPrimaryContainer = Color(0xFF001843),
        secondary = Secondary40,
        onSecondary = Color.White,
        secondaryContainer = Secondary90,
        onSecondaryContainer = Color(0xFF190E3A),
        tertiary = Tertiary40,
        onTertiary = Color(0xFF351F00),
        tertiaryContainer = Tertiary90,
        onTertiaryContainer = Color(0xFF2A1700),
        background = Neutral99,
        onBackground = Neutral10,
        surface = Neutral99,
        onSurface = Neutral10,
        surfaceVariant = NeutralVariant90,
        onSurfaceVariant = NeutralVariant30,
        outline = NeutralVariant50,
    )

internal val WeekDarkColorScheme =
    darkColorScheme(
        primary = Primary80,
        onPrimary = Color(0xFF002B7B),
        primaryContainer = Color(0xFF1143B1),
        onPrimaryContainer = Primary90,
        secondary = Secondary80,
        onSecondary = Color(0xFF271C5B),
        secondaryContainer = Color(0xFF3B2F74),
        onSecondaryContainer = Secondary90,
        tertiary = Tertiary80,
        onTertiary = Color(0xFF3D2700),
        tertiaryContainer = Color(0xFF5A3900),
        onTertiaryContainer = Tertiary90,
        background = Neutral10,
        onBackground = Neutral99,
        surface = Neutral20,
        onSurface = Neutral95,
        surfaceVariant = Color(0xFF2E333B),
        onSurfaceVariant = NeutralVariant80,
        outline = NeutralVariant50,
    )

object WeekColors {
    val Primary = Primary40
    val Secondary = Secondary40
    val AccentBookmark = Tertiary40
    val container = NeutralVariant90
    val NeutralBackground = Neutral99
    val NeutralOnBackground = Neutral10
}
