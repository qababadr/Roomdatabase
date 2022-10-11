package com.dev.roomdatabase.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Color(0xFFc38370),
    primaryVariant = Color(0xFFf8b39e),
    onPrimary = White,
    secondary = Color(0xFFe2aaa1),
    secondaryVariant = Color(0xFFffdcd2),
    onSecondary = Color.Black.copy(0.65f),
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Gray400,
    onBackground = Color.Black,
    surface = Gray300,
    onSurface = Color.Black,
)

private val DarkThemeColors = darkColors(
    primary = Color(0xFFc38370),
    primaryVariant = Color(0xFFf8b39e),
    onPrimary = White,
    secondary = Color(0xFFe2aaa1),
    secondaryVariant = Color(0xFFffdcd2),
    error = RedErrorLight,
    background = Black2,
    onBackground = White,
    surface = Black3,
    onSurface = White,
)

@Composable
fun RoomDatabaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = NunitoTypography,
        shapes = AppShapes,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface)
        ) {
            content()
        }
    }
}