package com.example.sauexpert.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
    MaterialTheme(
        colors = colors,
        typography =appTypography,
        shapes = Shapes,
        content = content
    )
}