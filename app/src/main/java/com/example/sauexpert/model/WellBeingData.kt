package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import com.example.sauexpert.ui.theme.Green15B83D

data class WellBeingData(
    val positionOnX: Float = 0f,
    val wellBeing: String = "",
    val positionOnY: Float = 0f,
    val dateName: String = "",
    var colorFocus: Color = Green15B83D
)
