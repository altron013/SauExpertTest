package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color

data class GlucoseData(
    val positionOnX: Float = 0f,
    val glucoseBeforeFood: Float = 0f,
    val glucoseAfterFood: Float = 0f,
    val dateName: String = "16.12",
    var colorFocusBeforeFood: Color = Color(250, 218, 221),
    var colorFocusAfterFood: Color = Color(250, 218, 221)
)
