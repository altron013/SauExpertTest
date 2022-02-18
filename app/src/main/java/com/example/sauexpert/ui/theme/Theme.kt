package com.example.sauexpert.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import dimensions.Dimensions
import dimensions.smallDimensions
import dimensions.sw360Dimensions

private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Color.DarkGray,
    primaryVariant = BlackAccent,
    secondary = Red435B,
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    //onBackground = SuraceF9,
    onSurface = Color.Black
)

@Composable
fun SauExpertTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions
    MaterialTheme(
        colors = colors,
        typography = SauExpertTypography,
        shapes = Shapes,
        content = content
    )
}

object AppTheme {

    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}

val Dimens: Dimensions
    @Composable
    get() = AppTheme.dimens

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

private val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}
