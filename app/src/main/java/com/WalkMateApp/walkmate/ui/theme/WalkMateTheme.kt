package com.SoundScapeApp.soundscape.ui.theme

import android.app.Activity
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.WalkMateApp.walkmate.ui.theme.LightGray
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue
import com.WalkMateApp.walkmate.ui.theme.White

private val LightMode = WalkMateColorScheme(
    background = LightGray,
    onBackground =  White,
    primary = Color.Red,
    onPrimary = Color.Red,
    secondary = Color.Red,
    onSecondary = Color.Red,
    textColor = Color.Black,
    tint = Color.Black
)

private val DarkMode = WalkMateColorScheme(
    background = MidnightBlue,
    onBackground = TwilightBlue,
    primary = Color.Red,
    onPrimary =Color.Red,
    secondary = Color.Red,
    onSecondary = Color.Red,
    textColor = Color.White,
    tint = Color.White
)


private val SmallTypography = WalkMateTypography(
    titleLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
)

private val MediumTypography = WalkMateTypography(
    titleLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )
)

private val LargeTypography = WalkMateTypography(
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )
)

// Sizes
private val smallSizes = WalkMateSizes(
    large = 325.dp,
    medium = 265.dp,
    normal = 0.dp,
    small = 0.dp
)

private val mediumSizes = WalkMateSizes(
    large = 370.dp,
    medium = 300.dp,
    normal = 0.dp,
    small = 0.dp
)

private val largeSizes = WalkMateSizes(
    large = 425.dp,
    medium = 340.dp,
    normal = 0.dp,
    small = 0.dp
)


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WalkMateThemes(
    themeChoice: Int,
    activity: Activity,
    content: @Composable () -> Unit
) {
    val windows = calculateWindowSizeClass(activity = activity)
    val config = LocalConfiguration.current

    val colorScheme = when (themeChoice) {
        1 -> DarkMode
        2 ->  LightMode
        else -> throw IllegalArgumentException("Invalid theme choice: $themeChoice")
    }
    val rippleIndication = rememberRipple()

    val typography = when (windows.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360) {
                SmallTypography


            } else if (config.screenWidthDp <= 411) {
                MediumTypography

            } else {
                LargeTypography
            }
        }

        else -> {
            LargeTypography // Default typography for other size classes
        }

    }

    val sizes = when (windows.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360) {
                smallSizes

            } else if (config.screenWidthDp <= 411) {
                mediumSizes

            } else {
                largeSizes
            }
        }

        else -> {
            largeSizes
        }

    }

    CompositionLocalProvider(
        localWalkMateColorScheme provides colorScheme,
        localWalkMateTypography provides typography,
        localWalkMateSize provides sizes,
        LocalIndication provides rippleIndication,
        content = content
    )

    val darkTheme = isSystemInDarkTheme()
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars
        }
    }
}

object WalkMateThemes {
    val colorScheme: WalkMateColorScheme
        @Composable get() = localWalkMateColorScheme.current

    val typography: WalkMateTypography
        @Composable get() = localWalkMateTypography.current

    val sizes:WalkMateSizes
        @Composable get() = localWalkMateSize.current
}


