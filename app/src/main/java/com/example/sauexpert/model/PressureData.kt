package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import com.example.sauexpert.ui.theme.Gray50

data class PressureData(
    val positionOnX: Float = 0f,
    val pressureInAverage: String = "",
    val startPoint: Float = 0f,
    val endPoint: Float = 0f,
    val dateName: String = "",
    var colorFocus: Color = Gray50
)
