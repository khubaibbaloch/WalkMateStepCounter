package com.SoundScapeApp.soundscape.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


//Color
data class WalkMateColorScheme(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val textColor: Color,
    val tint:Color,
)


//Typography
data class WalkMateTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle, //Create playlist text
    val titleSmall: TextStyle,  //Song Title
    val bodyLarge: TextStyle, //Playlist name
    val bodyMedium: TextStyle, //Artist name //song counts
    val bodySmall: TextStyle,
//    val labelLarge: TextStyle,
//    val labelMedium: TextStyle,
//    val labelSmall: TextStyle
)

//Shapes

//Size
data class WalkMateSizes(
    val large: Dp,
    val medium: Dp,
    val normal: Dp,
    val small: Dp
)


val localWalkMateColorScheme = staticCompositionLocalOf {
    WalkMateColorScheme(
        background  = Color.Unspecified,
        onBackground = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        textColor = Color.Unspecified,
        tint = Color.Unspecified
    )
}


val localWalkMateTypography = staticCompositionLocalOf {
    WalkMateTypography(
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleSmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodySmall = TextStyle.Default,
    )
}


val localWalkMateSize = staticCompositionLocalOf {
    WalkMateSizes(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        small = Dp.Unspecified
    )
}