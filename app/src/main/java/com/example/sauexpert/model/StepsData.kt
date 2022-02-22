package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import com.example.sauexpert.ui.theme.Gray50

data class StepsData(
    val positionOnX: Float = 0f,
    val stepsPerDay: Int = 0,
    val positionOnY: Float = 0f,
    val dateName: String = "",
    var colorFocus: Color = Gray50
)
