package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color

data class PressureData(
    val positionOnX: Float = 0f,
    val pressureInAverage: Float = 0f,
    val startPoint: Float = 0f,
    val dateName: String = "",
    var colorFocus: Color = Color(250, 218, 221)
)
